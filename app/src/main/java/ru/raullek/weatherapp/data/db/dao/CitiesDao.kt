package ru.raullek.weatherapp.data.db.dao

import androidx.room.*
import io.reactivex.Observable
import ru.raullek.weatherapp.domain.model.CityModel

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(cityModel: CityModel)

    @Query("SELECT * from citymodel")
    fun getAllCity(): Observable<List<CityModel>>

    @Delete
    fun removeCity(cityModel: CityModel)
}