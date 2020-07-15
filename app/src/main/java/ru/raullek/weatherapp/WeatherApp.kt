package ru.raullek.weatherapp

import android.app.Application
import ru.raullek.weatherapp.di.component.AppComponent
import ru.raullek.weatherapp.di.component.DaggerAppComponent
import ru.raullek.weatherapp.di.module.AppModule

class WeatherApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build().also {
                it.inject(this)
            }
    }

}