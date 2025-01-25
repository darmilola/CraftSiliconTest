package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Weather(
    @SerialName("id") val id: Long = 0,
    @SerialName("main") val main: String = "",
    @SerialName("description") val description: String = ""): Parcelable