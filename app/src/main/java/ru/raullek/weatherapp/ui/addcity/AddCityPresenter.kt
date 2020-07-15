package ru.raullek.weatherapp.ui.addcity

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SearchView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_city.*
import ru.raullek.weatherapp.domain.interactor.AddCityInteractor
import ru.raullek.weatherapp.domain.model.City
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddCityPresenter @Inject constructor(val interactor: AddCityInteractor) :
    AddCityMvpPresenter {
    private var view: AddCityMvpView? = null
    private var disposable = CompositeDisposable()

    private fun initSearch(name: String?) {
        disposable.add(
            interactor
                .initSearch(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showCities(it)
                }, {
                    view?.onError(it.localizedMessage)
                })
        )


    }

    override fun initSearch(edit_text: EditText) {
        disposable.add(Observable.create(ObservableOnSubscribe<String> { subscriber ->
            edit_text.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    subscriber.onNext(p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        }).map { text -> text.toLowerCase().trim() }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribe { text ->
                initSearch(text)
            }
        )

    }

    override fun addCity(city: City) {
        disposable.add(
            interactor.addCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showMessage("Город добавлен в список")
                }, {})
        )


    }


    override fun onAttach(view: AddCityMvpView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
        disposable.dispose()
    }


}