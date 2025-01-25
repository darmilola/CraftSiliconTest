package com.assignment.craftsilicontest.presentation

import com.assignment.craftsilicontest.di.ApiService
import com.assignment.craftsilicontest.domain.models.AppUIStates
import com.assignment.craftsilicontest.domain.repository.RemoteRepository
import com.assignment.craftsilicontest.domain.repository.RemoteRepositoryImpl
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.badoo.reaktive.single.subscribe

class MainPresenter(apiService: ApiService): MainContract.Presenter() {

    private val scope: CoroutineScope = MainScope()
    private var mainWeatherView: MainContract.MainWeatherView? = null
    private var searchCityView: MainContract.SearchCityView? = null
    private val remoteRepositoryImpl: RemoteRepositoryImpl = RemoteRepositoryImpl(apiService)


    override fun registerMainWeatherContract(view: MainContract.MainWeatherView?) {
        mainWeatherView = view
    }

    override fun registerSearchCityContract(view: MainContract.SearchCityView?) {
        searchCityView = view
    }

    override fun searchCity(searchValue: String) {
        searchCityView?.showSearchCityLce(AppUIStates(isLoading = true, loadingMessage = "Searching City..."))
        scope.launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) {
                    remoteRepositoryImpl.searchCity(searchValue)
                        .subscribe(
                            onSuccess = { result ->
                                searchCityView?.showSearchCityLce(AppUIStates(isSuccess = true))
                                searchCityView?.showCities(result)
                            },
                            onError = {
                                searchCityView?.showSearchCityLce(AppUIStates(isFailed = true, errorMessage = "Error Getting City"))
                            },
                        )
                }
                result.dispose()
            } catch(e: Exception) {
                searchCityView?.showSearchCityLce(AppUIStates(isFailed = true, errorMessage = "Error Getting City"))
            }
        }
    }

    override fun getForecast(lat: Double, lon: Double) {
        mainWeatherView?.showForecastLce(AppUIStates(isLoading = true, loadingMessage = "Getting Forecast..."))
        scope.launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) {
                    remoteRepositoryImpl.getForecast(lat, lon)
                        .subscribe(
                            onSuccess = { result ->
                                mainWeatherView?.showForecastLce(AppUIStates(isSuccess = true))
                                mainWeatherView?.showForecasts(result)
                            },
                            onError = {
                                mainWeatherView?.showForecastLce(AppUIStates(isFailed = true, errorMessage = "Error Getting Forecast"))
                            },
                        )
                }
                result.dispose()
            } catch(e: Exception) {
                mainWeatherView?.showForecastLce(AppUIStates(isFailed = true, errorMessage = "Error Getting Forecast"))
            }
        }
    }

    override fun getWeather(lat: Double, lon: Double) {
        mainWeatherView?.showMainWeatherLce(AppUIStates(isLoading = true, loadingMessage = "Getting Forecast..."))
        scope.launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) {
                    remoteRepositoryImpl.getWeather(lat, lon)
                        .subscribe(
                            onSuccess = { result ->
                                mainWeatherView?.showMainWeatherLce(AppUIStates(isSuccess = true))
                                mainWeatherView?.showMainWeather(result)
                            },
                            onError = {
                                println("Error 0 ${it.message}")
                                mainWeatherView?.showMainWeatherLce(AppUIStates(isFailed = true, errorMessage = "Error Getting Weather"))
                            },
                        )
                }
                result.dispose()
            } catch(e: Exception) {
                println("Error 1 ${e.message}")
                mainWeatherView?.showMainWeatherLce(AppUIStates(isFailed = true, errorMessage = "Error Getting Weather"))
            }
        }
    }

}