package ru.sberbank.weatherapp.di.module

import dagger.Module
import dagger.Provides
import ru.sberbank.weatherapp.di.ActivityScope
import ru.sberbank.weatherapp.ui.main.MainScreenMvpPresenter
import ru.sberbank.weatherapp.ui.main.MainScreenPresenter
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @ActivityScope
    fun provideMainScreenPresenter(mainScreenPresenter: MainScreenPresenter): MainScreenMvpPresenter =
        mainScreenPresenter
}