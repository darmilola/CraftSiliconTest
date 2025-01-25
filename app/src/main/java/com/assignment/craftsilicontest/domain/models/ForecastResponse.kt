package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ForecastResponse(
    @SerialName("list") val forecasts: List<Forecast>? = null,
): Parcelable