package ru.raullek.weatherapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.activity_main_screen.*
import ru.raullek.weatherapp.R
import ru.raullek.weatherapp.WeatherApp
import ru.raullek.weatherapp.di.component.DaggerActivityComponent
import ru.raullek.weatherapp.di.module.ActivityModule
import ru.raullek.weatherapp.domain.helper.SwipeToDeleteCallback
import ru.raullek.weatherapp.domain.model.CityModel
import ru.raullek.weatherapp.ui.addcity.AddCityActivity
import ru.raullek.weatherapp.ui.weatherdetail.CityForecastActivity
import javax.inject.Inject


class MainScreenActivity : AppCompatActivity(), MainScreenMvpView {
    @Inject
    lateinit var presenter: MainScreenMvpPresenter
    lateinit var localCitiesAdapter: LocalCitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        presenter.onAttach(this)
        setContentView(R.layout.activity_main_screen)
        setUp()
    }

    private fun setUp() {
        presenter.getLocalCities()
        localCitiesAdapter = LocalCitiesAdapter { goToDetailScreen(it) }
        setToolBar()
        enableSwipeToDelete()
    }

    private fun setToolBar() {
        setSupportActionBar(main_toolbar)
    }

    private fun injectDependencies() {
        DaggerActivityComponent
            .builder()
            .appComponent((application as WeatherApp).appComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    fun goToDetailScreen(cityModel: CityModel) {
        val intent = Intent(this,CityForecastActivity::class.java).also {
            it.putExtra(CityForecastActivity.CITY_KEY,cityModel)
        }
        startActivity(intent)

    }

    override fun showCities(cities: List<CityModel>) {
        cities_list_rv.apply {
            layoutManager =
                LinearLayoutManager(this@MainScreenActivity, RecyclerView.VERTICAL, false)
            cities_list_rv.adapter = localCitiesAdapter
        }
        localCitiesAdapter.setList(cities)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.add_city_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        while (item.itemId == R.id.go_to_add_city) {
            startActivity(Intent(this, AddCityActivity::class.java))
            return true
        }
        return false
    }

    private fun enableSwipeToDelete() {
        val swipeToDeleteCallback= object : SwipeToDeleteCallback(this) {
            override fun onSwiped(@NonNull viewHolder: ViewHolder, i: Int) {
                val position = viewHolder.adapterPosition
                val item: CityModel = localCitiesAdapter.getData().get(position)
                localCitiesAdapter.removeItem(position)
                presenter.removeItem(item)
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(cities_list_rv)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}