package ru.raullek.weatherapp.ui.weatherdetail

import ru.raullek.weatherapp.domain.model.forecast.WeekForecast

interface CityForecastMvpView{
    fun showWeekForecast(weekForecast: WeekForecast?)
    fun onError(message:String?)
    fun showLoading()
    fun hideLoading()


}

interface CityForecastMvpPresenter{
    fun getCityForecast(lat:String,lon:String)
    fun onAttach(mvpView: CityForecastMvpView)
    fun onDetach()

}