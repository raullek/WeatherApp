package ru.raullek.weatherapp.di.module

import dagger.Module
import dagger.Provides
import ru.raullek.weatherapp.di.ActivityScope
import ru.raullek.weatherapp.ui.addcity.AddCityMvpPresenter
import ru.raullek.weatherapp.ui.addcity.AddCityPresenter
import ru.raullek.weatherapp.ui.main.MainScreenMvpPresenter
import ru.raullek.weatherapp.ui.main.MainScreenPresenter
import ru.raullek.weatherapp.ui.weatherdetail.CityForecastMvpPresenter
import ru.raullek.weatherapp.ui.weatherdetail.CityForecastPresenter

@Module
class PresenterModule {

    @Provides
    @ActivityScope
    fun provideMainScreenPresenter(mainScreenPresenter: MainScreenPresenter): MainScreenMvpPresenter =
        mainScreenPresenter

    @Provides
    @ActivityScope
    fun provideAddCityPresenter(addCityPresenter: AddCityPresenter): AddCityMvpPresenter =
        addCityPresenter

    @Provides
    @ActivityScope
    fun provideForecastPresenter(forecastPresenter: CityForecastPresenter): CityForecastMvpPresenter =
        forecastPresenter
}