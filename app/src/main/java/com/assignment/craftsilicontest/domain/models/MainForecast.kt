package com.assignment.craftsilicontest.domain.models

import android.health.connect.datatypes.units.Temperature
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class MainForecast(
    @SerialName("temp") val temperature: Double = 0.0,
    @SerialName("feels_like") val feelsLike: Double = 0.0,
    @SerialName("temp_min") val minTemperature: Double = 0.0,
    @SerialName("temp_max") val maxTemperature: Double = 0.0,
    @SerialName("humidity") val humidity: Double = 0.0): Parcelable
