package com.assignment.craftsilicontest.di

import com.assignment.craftsilicontest.domain.models.City
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.domain.models.ForecastResponse
import com.badoo.reaktive.single.toSingle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {

    companion object {
        private const val WEATHER_END_POINT = "https://api.openweathermap.org/data/2.5/weather?appid=cfe577b09f43deea2722462eea76e473&units=metric"
        private const val FORECAST_END_POINT = "https://api.openweathermap.org/data/2.5/forecast?appid=cfe577b09f43deea2722462eea76e473&units=metric&cnt=5"
        private const val CITY_END_POINT = "http://api.openweathermap.org/geo/1.0/direct?limit=5&appid=cfe577b09f43deea2722462eea76e473"
    }

    suspend fun getWeather(lat: Double, lon: Double) = client.get("$WEATHER_END_POINT&lat = $lat&lon = $lon").body<CityWeather>().toSingle()

    suspend fun getForecast(lat: Double, lon: Double) = client.get("$FORECAST_END_POINT&lat = $lat&lon = $lon").body<ForecastResponse>().toSingle()

    suspend fun searchCity(searchValue: String) = client.get("$CITY_END_POINT&q=$searchValue").body<List<City>>().toSingle()

}