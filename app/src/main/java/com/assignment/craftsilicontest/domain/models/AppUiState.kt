package com.assignment.craftsilicontest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AppUIStates(
    val isLoading: Boolean = false,
    var isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val loadingMessage: String = "",
    val successMessage: String = "",
    val emptyMessage: String = "",
    val errorMessage: String = "",): Parcelable
