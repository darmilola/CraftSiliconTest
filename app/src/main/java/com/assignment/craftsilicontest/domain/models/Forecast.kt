package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Forecast(
    @SerialName("dt") val date: Long = 0L,
    @SerialName("main") val main: MainForecast? = null,
    @SerialName("weather") val weather: List<Weather>? = null,
    @SerialName("wind") val wind: List<Wind>? = null,
    @SerialName("visibility") val visibility: Long? = 0L,
    @SerialName("dt_txt") val dateText: String = "",
): Parcelable
