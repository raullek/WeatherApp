package ru.raullek.weatherapp.ui.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.raullek.weatherapp.domain.interactor.MainInteractor
import ru.raullek.weatherapp.domain.model.CityModel
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(private val interactor: MainInteractor) :
    MainScreenMvpPresenter {
    private var view: MainScreenMvpView? = null
    private var disposable = CompositeDisposable()


    override fun onAttach(mainScreenMvpView: MainScreenMvpView) {
        this.view = mainScreenMvpView
    }

    override fun onDetach() {
        this.view = null
        disposable.dispose()
    }

    override fun getLocalCities() {
        disposable.add(
            interactor
                .getLocalCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showCities(it)
                }, {
                })
        )

    }

    override fun removeItem(cityModel: CityModel) {
        disposable.add(
            interactor
                .removeItem(cityModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                })
        )
    }


}