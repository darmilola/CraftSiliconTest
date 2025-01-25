package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
@Entity
data class ForecastResponse(
    @SerialName("list") val forecasts: List<Forecast>? = null,
    @PrimaryKey @SerialName("cnt") val count: Int? = null,
): Parcelable