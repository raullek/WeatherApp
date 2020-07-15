package ru.raullek.weatherapp.domain.interactor

import io.reactivex.Observable
import io.reactivex.Single
import ru.raullek.weatherapp.data.repository.WeatherRepository
import ru.raullek.weatherapp.domain.model.forecast.WeekForecast
import javax.inject.Inject

class ForecastInteractor @Inject constructor(private val weatherRepository: WeatherRepository)
    : UseCase {

    fun getCityForecast(lat:String,lon:String):Single<WeekForecast>{
        return weatherRepository.getWeekForecast(lat,lon)
    }
}