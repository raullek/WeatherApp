package ru.raullek.weatherapp.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.raullek.weatherapp.domain.model.forecast.WeekForecast

interface OpenWeatherApi {

    @GET("data/2.5/onecall?exclude=daily&units=metric")
    fun getWeeklyCityForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") key: String
    ): Single<WeekForecast>
}