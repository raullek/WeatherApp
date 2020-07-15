package ru.sberbank.weatherapp.data.repository

import androidx.lifecycle.LiveData
import io.reactivex.Observable
import retrofit2.Retrofit
import ru.sberbank.weatherapp.data.api.CitiesApi
import ru.sberbank.weatherapp.domain.model.City
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