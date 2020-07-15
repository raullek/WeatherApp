package ru.sberbank.weatherapp.domain.interactor

import ru.sberbank.weatherapp.data.repository.CitiesLocalRepository
import ru.sberbank.weatherapp.domain.model.CityModel
import javax.inject.Inject

class MainInteractor @Inject constructor(private val citiesLocalRepository: CitiesLocalRepository) :
    UseCase {
    fun getLocalCities() = citiesLocalRepository.getAllCities()

    fun removeItem(cityModel: CityModel) = citiesLocalRepository.removeCity(cityModel)

}