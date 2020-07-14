package ru.sberbank.weatherapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import ru.sberbank.weatherapp.di.ActivityScope
import ru.sberbank.weatherapp.domain.interactor.MainInteractor
import ru.sberbank.weatherapp.domain.interactor.UseCase

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



}