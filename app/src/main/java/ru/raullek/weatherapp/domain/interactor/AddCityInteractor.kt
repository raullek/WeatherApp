package ru.raullek.weatherapp.domain.interactor

import io.reactivex.Completable
import ru.raullek.weatherapp.data.repository.CitiesLocalRepository
import ru.raullek.weatherapp.data.repository.CitiesRemoteRepository
import ru.raullek.weatherapp.domain.model.City
import ru.raullek.weatherapp.domain.model.CityModel
import javax.inject.Inject

class AddCityInteractor @Inject constructor(
    private val citiesRemoteRepository: CitiesRemoteRepository,
    private val citiesLocalRepository: CitiesLocalRepository
) : UseCase {

    fun initSearch(name: String?) = citiesRemoteRepository.getSearchedCities(name)

    fun addCity(city: City): Completable {
        return citiesLocalRepository.addCity(CityModel.mapToCityModel(city))
    }

}