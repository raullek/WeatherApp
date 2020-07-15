package ru.sberbank.weatherapp.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url
import ru.sberbank.weatherapp.domain.model.City

interface CitiesApi {
    @GET
    fun getSearchedCities(@Url url: String):Observable<List<City>>

}