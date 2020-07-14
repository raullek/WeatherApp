package ru.sberbank.weatherapp

import android.app.Application
import ru.sberbank.weatherapp.di.component.AppComponent
import ru.sberbank.weatherapp.di.component.DaggerAppComponent
import ru.sberbank.weatherapp.di.module.AppModule

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