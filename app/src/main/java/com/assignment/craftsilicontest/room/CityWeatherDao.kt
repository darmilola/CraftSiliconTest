package com.assignment.craftsilicontest.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.assignment.craftsilicontest.domain.models.CityWeather

@Dao
interface CityWeatherDao {
    @Insert
    suspend fun insert(cityWeather: CityWeather)

    @Update
    suspend fun update(cityWeather: CityWeather)

    @Delete
    suspend fun delete(cityWeather: CityWeather)

    @Query("SELECT * FROM CityWeather")
    fun getCityWeather(): List<CityWeather>

    @Query("DELETE FROM CityWeather")
    fun deleteWeatherEntry()

    @Query("DELETE FROM CityWeather WHERE id = :weatherId")
    suspend fun deleteWeatherId(weatherId: Long)
}