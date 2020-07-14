package ru.sberbank.weatherapp.di.component

import dagger.Component
import ru.sberbank.weatherapp.WeatherApp
import ru.sberbank.weatherapp.di.module.AppModule
import ru.sberbank.weatherapp.di.module.NetworkModule
import ru.sberbank.weatherapp.data.repository.DataPreferences
import ru.sberbank.weatherapp.data.repository.WeatherRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class])
interface AppComponent {
    fun inject(weatherApp: WeatherApp)

    fun getPreferences(): DataPreferences
    fun getWeatherRepository():WeatherRepository
}