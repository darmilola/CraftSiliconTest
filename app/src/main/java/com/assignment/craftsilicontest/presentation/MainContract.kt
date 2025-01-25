package com.assignment.craftsilicontest.presentation

import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast

class MainContract {

    interface MainWeatherView {
        fun showMainWeatherLce(appUIStates: AppUIStates)
        fun showForecastLce(appUIStates: AppUIStates)
        fun showMainWeather(cityWeather: CityWeather)
        fun showForecasts(forecasts: List<Forecast>)
    }

    interface SearchCityView {
        fun showSearchCityLce(appUIStates: AppUIStates)
        fun showCities(cities: List<City>)
    }

    abstract class Presenter {
        abstract fun registerMainWeatherContract(view: MainWeatherView?)
        abstract fun registerSearchCityContract(view: SearchCityView?)
        abstract fun searchCity(searchValue: String)
        abstract fun getForecast(lat: Double, lon: Double)
        abstract fun getWeather(lat: Double, lon: Double)
    }
}