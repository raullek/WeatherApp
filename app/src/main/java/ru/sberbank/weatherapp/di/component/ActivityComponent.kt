package ru.sberbank.weatherapp.di.component

import dagger.Component
import ru.sberbank.weatherapp.di.ActivityScope
import ru.sberbank.weatherapp.di.module.ActivityModule
import ru.sberbank.weatherapp.di.module.PresenterModule
import ru.sberbank.weatherapp.ui.addcity.AddCityActivity
import ru.sberbank.weatherapp.ui.main.MainScreenActivity

@ActivityScope
@Component(modules = [ActivityModule::class,PresenterModule::class],dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(mainScreenActivity: MainScreenActivity)
    fun inject(addCityActivity: AddCityActivity)
}