package ru.sberbank.weatherapp.ui.addcity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_add_city.*
import ru.sberbank.weatherapp.R
import ru.sberbank.weatherapp.WeatherApp
import ru.sberbank.weatherapp.di.component.DaggerActivityComponent
import ru.sberbank.weatherapp.di.module.ActivityModule
import ru.sberbank.weatherapp.domain.model.City
import ru.sberbank.weatherapp.ui.main.MainScreenMvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddCityActivity : AppCompatActivity(), AddCityMvpView {
    @Inject
    lateinit var presenter: AddCityMvpPresenter
    private lateinit var disposable: Disposable
    lateinit var citiesAdapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        presenter.onAttach(this)
        setContentView(R.layout.activity_add_city)
        setUp()

    }


    private fun setUp() {
        citiesAdapter = CitiesAdapter { city -> presenter.addCity(city) }
        initToolbar()
        initSearch()

    }

    private fun initSearch() {
        disposable = Observable.create(ObservableOnSubscribe<String> { subscriber ->
            edit_name.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    subscriber.onNext(p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }).map { text -> text.toLowerCase().trim() }
            .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe { text ->
                presenter.initSearch(text)
            }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .appComponent((application as WeatherApp).appComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun showCities(cities: List<City>) {
        cities_rv.apply {
            layoutManager =
                LinearLayoutManager(this@AddCityActivity, RecyclerView.VERTICAL, false)
            cities_rv.adapter = citiesAdapter
        }
        citiesAdapter.setList(cities)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    override fun onError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}