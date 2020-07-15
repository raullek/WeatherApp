package ru.raullek.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.raullek.weatherapp.data.db.dao.CitiesDao
import ru.raullek.weatherapp.domain.model.CityModel

@Database(
    entities = [CityModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCityDao(): CitiesDao
}