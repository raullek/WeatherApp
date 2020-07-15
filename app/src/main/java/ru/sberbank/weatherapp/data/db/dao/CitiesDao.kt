package ru.sberbank.weatherapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import ru.sberbank.weatherapp.domain.model.CityModel

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(cityModel: CityModel)

    @Query("SELECT * from citymodel")
    fun getAllCity(): Observable<List<CityModel>>
}