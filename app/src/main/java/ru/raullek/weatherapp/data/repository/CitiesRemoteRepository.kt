package ru.raullek.weatherapp.data.repository

import io.reactivex.Observable
import retrofit2.Retrofit
import ru.raullek.weatherapp.data.api.CitiesApi
import ru.raullek.weatherapp.domain.model.City
import javax.inject.Inject

interface CitiesRemoteRepository {
    fun getSearchedCities(cityName: String?): Observable<List<City>>

}

class CitiesRemoteRepositoryImpl @Inject constructor(val client: Retrofit) :
    CitiesRemoteRepository {

    override fun getSearchedCities(cityName: String?): Observable<List<City>> {
        val url = "https://nominatim.openstreetmap.org/search?q=${cityName}&format=json"
        return client.create(CitiesApi::class.java)
            .getSearchedCities(url)
    }

}