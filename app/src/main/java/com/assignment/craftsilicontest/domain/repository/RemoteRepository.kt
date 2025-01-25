package com.assignment.craftsilicontest.domain.repository

import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.domain.models.ForecastResponse
import com.badoo.reaktive.single.Single

interface RemoteRepository {
    suspend fun searchCity(searchValue: String): Single<List<City>>
    suspend fun getForecast(lat: Double, lon: Double): Single<ForecastResponse>
    suspend fun getWeather(lat: Double, lon: Double): Single<CityWeather>
}