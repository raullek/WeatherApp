package ru.sberbank.weatherapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.sberbank.weatherapp.data.repository.DataPreferences
import ru.sberbank.weatherapp.data.repository.DataPreferencesImpl
import ru.sberbank.weatherapp.data.repository.WeatherRepository
import ru.sberbank.weatherapp.data.repository.WeatherRepositoryImpl
import javax.inject.Singleton

@Module
class AppModule(private val context: Application) {
    companion object {
        const val preferencesName = "PREF_NAME"
    }

    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun getSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun getPreferences(dataPreferences: DataPreferencesImpl): DataPreferences = dataPreferences

    @Provides
    @Singleton
    fun getWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):WeatherRepository = weatherRepositoryImpl


}