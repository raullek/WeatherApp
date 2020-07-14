package ru.sberbank.weatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sberbank.weatherapp.R
import ru.sberbank.weatherapp.WeatherApp
import ru.sberbank.weatherapp.di.component.DaggerActivityComponent
import ru.sberbank.weatherapp.di.module.ActivityModule
import javax.inject.Inject

class MainScreenActivity : AppCompatActivity(), MainScreenMvpView {
    @Inject
    lateinit var mainScreenMvpPresenter: MainScreenMvpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        mainScreenMvpPresenter.onAttach(this)
        setContentView(R.layout.activity_main_screen)


    }

    private fun injectDependencies(){
        DaggerActivityComponent
            .builder()
            .appComponent((application as WeatherApp).appComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }
}