package ru.sberbank.weatherapp.domain.interactor

import io.reactivex.Completable
import ru.sberbank.weatherapp.data.repository.CitiesLocalRepository
import ru.sberbank.weatherapp.data.repository.CitiesRemoteRepository
import ru.sberbank.weatherapp.domain.model.City
import ru.sberbank.weatherapp.domain.model.CityModel
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