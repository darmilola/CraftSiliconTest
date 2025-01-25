package com.assignment.craftsilicontest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.assignment.craftsilicontest.domain.models.CityWeather
import com.assignment.craftsilicontest.domain.models.Coordinate
import com.assignment.craftsilicontest.domain.models.Forecast
import com.assignment.craftsilicontest.domain.models.ForecastResponse
import com.assignment.craftsilicontest.domain.models.MainForecast
import com.assignment.craftsilicontest.domain.models.Weather
import com.assignment.craftsilicontest.domain.models.Wind
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Database(entities = [CityWeather::class, ForecastResponse::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
    abstract fun forecastDao(): ForecastDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromWeatherList(value : List<Weather>) = Json.encodeToString(value)

    @TypeConverter
    fun toWeatherList(value: String) = Json.decodeFromString<List<Weather>>(value)

    @TypeConverter
    fun fromCoordinate(value : Coordinate) = Json.encodeToString(value)

    @TypeConverter
    fun toCoordinate(value: String) = Json.decodeFromString<Coordinate>(value)

    @TypeConverter
    fun fromMainForecast(value : MainForecast) = Json.encodeToString(value)

    @TypeConverter
    fun toMainForecast(value: String) = Json.decodeFromString<MainForecast>(value)

    @TypeConverter
    fun fromWind(value : Wind) = Json.encodeToString(value)

    @TypeConverter
    fun toWind(value: String) = Json.decodeFromString<Wind>(value)

    @TypeConverter
    fun fromForecastList(value : List<Forecast>) = Json.encodeToString(value)

    @TypeConverter
    fun toForecastList(value: String) = Json.decodeFromString<List<Forecast>>(value)


}