package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CityWeather(
    @SerialName("coord") val coordinate: Coordinate? = null,
    @SerialName("weather") val weather: Weather? = null,
    @SerialName("main") val mainForecast: MainForecast? = null,
    @SerialName("visibility") val visibility: Long = 0L,
    @SerialName("wind") val wind: Wind? = null,
    @SerialName("lon") val longitude: Double = 0.0,
    @SerialName("name") val cityName: String = ""
): Parcelable