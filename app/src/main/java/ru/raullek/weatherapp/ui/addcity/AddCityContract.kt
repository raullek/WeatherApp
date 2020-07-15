package ru.raullek.weatherapp.ui.addcity

import ru.raullek.weatherapp.domain.model.City

interface AddCityMvpView{
    fun showCities(cities:List<City>)
    fun showMessage(message:String)
    fun onError(errorMessage:String)

}

interface AddCityMvpPresenter{
    fun initSearch(name:String?)
    fun addCity(city: City)
    fun onAttach(view: AddCityMvpView)
    fun onDetach()

}