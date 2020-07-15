package ru.raullek.weatherapp.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.raullek.weatherapp.data.db.dao.CitiesDao
import ru.raullek.weatherapp.domain.model.CityModel
import javax.inject.Inject

interface CitiesLocalRepository {
    fun addCity(cityModel: CityModel): Completable
    fun getAllCities():Observable<List<CityModel>>
    fun removeCity(cityModel: CityModel):Completable
}

class CitiesLocalRepositoryImpl @Inject constructor(private val citiesDao: CitiesDao) :
    CitiesLocalRepository {

    override fun addCity(cityModel: CityModel): Completable {
        return Completable.fromCallable {
            citiesDao.insertCity(cityModel)
        }
    }

    override fun getAllCities(): Observable<List<CityModel>> {
        return citiesDao.getAllCity()
    }

    override fun removeCity(cityModel: CityModel):Completable{
        return Completable.fromCallable {
            citiesDao.removeCity(cityModel)
        }
    }


}