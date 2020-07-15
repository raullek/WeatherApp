package ru.raullek.weatherapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class CityModel(
    @PrimaryKey val placeId: Int,
    val latitude: String,
    val longitude: String,
    val displayName: String,
    val placeIconUrl: String

) : Serializable {
    companion object {
        fun mapToCityModel(city: City): CityModel {
            return CityModel(city.placeId, city.lat, city.lon, city.displayName, city.icon)
        }
    }
}