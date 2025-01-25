package com.assignment.craftsilicontest.presentation.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.assignment.craftsilicontest.domain.models.AppUIStates
import kotlinx.coroutines.flow.StateFlow

class LoadingViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private var _uiState = savedStateHandle.getStateFlow("loadingScreenUiState", AppUIStates())
    val uiStateInfo: StateFlow<AppUIStates>
        get() = _uiState

    fun switchScreenUIState(appUIStates: AppUIStates) {
        savedStateHandle["loadingScreenUiState"] = appUIStates
    }
}