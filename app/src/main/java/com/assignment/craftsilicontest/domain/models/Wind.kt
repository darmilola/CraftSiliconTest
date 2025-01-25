package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Wind(
    @SerialName("speed") val speed: Double = 0.0,
    @SerialName("deg") val degree: Long = 0L,
    @SerialName("gust") val gust: Double = 0.0
): Parcelable