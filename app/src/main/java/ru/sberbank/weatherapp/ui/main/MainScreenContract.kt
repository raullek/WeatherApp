package ru.sberbank.weatherapp.ui.main

import ru.sberbank.weatherapp.domain.model.CityModel

interface MainScreenMvpView{
    fun showCities(cities:List<CityModel>)


}

interface MainScreenMvpPresenter{
    fun onAttach(mainScreenMvpView: MainScreenMvpView)
    fun onDetach()
    fun getLocalCities()
    fun removeItem(cityModel: CityModel)

}