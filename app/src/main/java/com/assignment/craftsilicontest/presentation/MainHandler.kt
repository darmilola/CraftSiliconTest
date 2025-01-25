package com.assignment.craftsilicontest.presentation

import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.presentation.ViewModel.LoadingViewModel

class MainHandler(
    private val mainLoadingViewModel: LoadingViewModel,
    private val forecastLoadingViewModel: LoadingViewModel,
    private val mainPresenter: MainPresenter,
    private val onMainWeatherAvailable: (CityWeather) -> Unit,
    private val onForecastAvailable: (List<Forecast>) -> Unit) : MainContract.MainWeatherView {

    fun init() {
        mainPresenter.registerMainWeatherContract(this)
    }

    override fun showMainWeatherLce(appUIStates: AppUIStates) {
        mainLoadingViewModel.switchScreenUIState(appUIStates)
    }

    override fun showForecastLce(appUIStates: AppUIStates) {
        forecastLoadingViewModel.switchScreenUIState(appUIStates)
    }

    override fun showMainWeather(cityWeather: CityWeather) {
        onMainWeatherAvailable(cityWeather)
    }

    override fun showForecasts(forecasts: List<Forecast>) {
        onForecastAvailable(forecasts)
    }


}
