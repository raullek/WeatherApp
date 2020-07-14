package ru.sberbank.weatherapp.ui.main

interface MainScreenMvpView{

}

interface MainScreenMvpPresenter{
    fun onAttach(mainScreenMvpView: MainScreenMvpView)
    fun onDetach()

}