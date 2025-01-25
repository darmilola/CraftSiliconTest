package com.assignment.craftsilicontest.domain.repository

import com.assignment.craftsilicontest.di.ApiService
import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.domain.models.ForecastResponse
import com.badoo.reaktive.single.Single

class RemoteRepositoryImpl(
    private val apiService: ApiService
) : RemoteRepository {

    override suspend fun searchCity(searchValue: String): Single<List<City>> {
        return apiService.searchCity(searchValue)
    }

    override suspend fun getForecast(lat: Double, lon: Double): Single<ForecastResponse> {
        return apiService.getForecast(lat,lon)
    }

    override suspend fun getWeather(lat: Double, lon: Double): Single<CityWeather> {
        return apiService.getWeather(lat, lon)
    }
}