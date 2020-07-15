package ru.raullek.weatherapp.ui.addcity

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.raullek.weatherapp.domain.interactor.AddCityInteractor
import ru.raullek.weatherapp.domain.model.City
import javax.inject.Inject

class AddCityPresenter @Inject constructor(val interactor: AddCityInteractor) :
    AddCityMvpPresenter {
    private var view: AddCityMvpView? = null
    private var disposable = CompositeDisposable()

    override fun initSearch(name: String?) {
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