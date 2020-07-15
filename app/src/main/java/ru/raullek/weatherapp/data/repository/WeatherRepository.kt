package ru.raullek.weatherapp.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import ru.raullek.weatherapp.data.api.OpenWeatherApi
import ru.raullek.weatherapp.domain.model.forecast.WeekForecast
import ru.raullek.weatherapp.utills.Constants
import javax.inject.Inject

interface WeatherRepository {

    fun getWeekForecast(latitude:String,longitude:String):Single<WeekForecast>



}

class WeatherRepositoryImpl @Inject constructor(val client: Retrofit) : WeatherRepository {
    override fun getWeekForecast(latitude:String,longitude:String): Single<WeekForecast> {
        return client
            .create(OpenWeatherApi::class.java)
            .getWeeklyCityForecast(latitude,longitude,Constants.API_KEY)
    }

}