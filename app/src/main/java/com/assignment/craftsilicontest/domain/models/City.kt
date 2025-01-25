package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class City(
    @SerialName("name") val cityName: String = "",
    @SerialName("country") val country: String = "",
    @SerialName("state") val state: String = "",
    @SerialName("lat") val latitude: Double = 0.0,
    @SerialName("lon") val longitude: Double = 0.0,
): Parcelable
