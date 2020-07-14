package ru.sberbank.weatherapp.ui.main

import ru.sberbank.weatherapp.domain.interactor.MainInteractor
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(mainInteractor: MainInteractor) :
    MainScreenMvpPresenter {
    private var view: MainScreenMvpView? = null


    override fun onAttach(mainScreenMvpView: MainScreenMvpView) {
        this.view = mainScreenMvpView
    }

    override fun onDetach() {
        this.view = null
    }


}