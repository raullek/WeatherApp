package ru.raullek.weatherapp.di.component

import dagger.Component
import ru.raullek.weatherapp.di.ActivityScope
import ru.raullek.weatherapp.di.module.ActivityModule
import ru.raullek.weatherapp.di.module.PresenterModule
import ru.raullek.weatherapp.ui.addcity.AddCityActivity
import ru.raullek.weatherapp.ui.main.MainScreenActivity
import ru.raullek.weatherapp.ui.weatherdetail.CityForecastActivity

@ActivityScope
@Component(modules = [ActivityModule::class,PresenterModule::class],dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(mainScreenActivity: MainScreenActivity)
    fun inject(addCityActivity: AddCityActivity)
    fun inject(cityForecastActivity: CityForecastActivity)
}