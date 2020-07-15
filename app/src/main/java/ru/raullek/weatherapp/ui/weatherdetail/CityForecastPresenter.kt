package ru.raullek.weatherapp.ui.weatherdetail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.raullek.weatherapp.domain.interactor.ForecastInteractor
import javax.inject.Inject

class CityForecastPresenter @Inject constructor(private val interactor: ForecastInteractor) :
    CityForecastMvpPresenter {
    private var view: CityForecastMvpView? = null
    private var disposable = CompositeDisposable()
    override fun getCityForecast(lat:String,lon:String) {
        disposable.add(interactor.getCityForecast(lat,lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view?.showLoading() }
            .doAfterTerminate { view?.hideLoading() }
            .subscribe({
                view?.showWeekForecast(it)
            },{
                view?.onError(it.localizedMessage)

            }))
    }


    override fun onAttach(mvpView: CityForecastMvpView) {
        this.view = mvpView
    }

    override fun onDetach() {
        this.view = null
        disposable.dispose()
    }


}