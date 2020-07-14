package ru.sberbank.weatherapp.data.repository

import android.content.SharedPreferences
import javax.inject.Inject

interface DataPreferences {
    fun saveString(key:String?,data: String?)
    fun saveInt(key:String?,data: Int)
}

class DataPreferencesImpl @Inject constructor(val preferences: SharedPreferences) : DataPreferences {

    override fun saveString(key:String?,data: String?) {
        preferences.edit().putString(key,data).apply()
    }

    override fun saveInt(key:String?,data: Int) {
        preferences.edit().putInt(key,data).apply()
    }

}