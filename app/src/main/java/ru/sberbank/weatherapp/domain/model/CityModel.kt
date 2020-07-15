package ru.sberbank.weatherapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CityModel(
    @PrimaryKey val placeId: Int,
    val latitude: String,
    val longitude: String,
    val displayName: String,
    val placeIconUrl: String

) {
    companion object {
        fun mapToCityModel(city: City): CityModel {
            return CityModel(city.placeId, city.lat, city.lon, city.displayName, city.icon)
        }
    }
}