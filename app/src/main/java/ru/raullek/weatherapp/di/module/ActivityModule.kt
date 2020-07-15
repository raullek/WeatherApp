package ru.raullek.weatherapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import ru.raullek.weatherapp.di.ActivityScope
import ru.raullek.weatherapp.domain.interactor.AddCityInteractor
import ru.raullek.weatherapp.domain.interactor.MainInteractor
import ru.raullek.weatherapp.domain.interactor.UseCase

@Module
class ActivityModule(private val context: AppCompatActivity) {

    @ActivityScope
    @Provides
    fun provideActivityContext(): Context = context

    @Provides
    @ActivityScope
    fun getMainInteractor(mainInteractor: MainInteractor): UseCase {
        return mainInteractor
    }

    @Provides
    @ActivityScope
    fun getAddCityInteractor(addCityInteractor: AddCityInteractor): UseCase {
        return addCityInteractor
    }



}