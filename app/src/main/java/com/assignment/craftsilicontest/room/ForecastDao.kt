package com.assignment.craftsilicontest.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.ForecastResponse

@Dao
interface ForecastDao {
    @Insert
    suspend fun insert(forecastResponse: ForecastResponse)

    @Query("SELECT * FROM ForecastResponse")
    fun getWeatherForecast(): List<ForecastResponse>

    @Query("DELETE FROM ForecastResponse")
    fun deleteWeatherForecast()
}