package ru.raullek.weatherapp.di.component

import dagger.Component
import ru.raullek.weatherapp.WeatherApp
import ru.raullek.weatherapp.data.repository.CitiesLocalRepository
import ru.raullek.weatherapp.data.repository.CitiesRemoteRepository
import ru.raullek.weatherapp.di.module.AppModule
import ru.raullek.weatherapp.di.module.NetworkModule
import ru.raullek.weatherapp.data.repository.DataPreferences
import ru.raullek.weatherapp.data.repository.WeatherRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class])
interface AppComponent {
    fun inject(weatherApp: WeatherApp)

    fun getPreferences(): DataPreferences
    fun getWeatherRepository():WeatherRepository
    fun getRemoteCitiesRepository():CitiesRemoteRepository
    fun getLocalCitiesRepository():CitiesLocalRepository
}