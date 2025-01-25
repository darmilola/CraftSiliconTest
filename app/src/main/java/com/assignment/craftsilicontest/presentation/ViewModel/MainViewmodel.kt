package com.assignment.craftsilicontest.presentation.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.assignment.craftsilicontest.domain.models.Coordinate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel(){
    private val _selectedCoordinate = MutableStateFlow(Coordinate())
    private val _shouldRefresh = MutableStateFlow(false)

    val selectedCoordinate: StateFlow<Coordinate> = _selectedCoordinate.asStateFlow()
    val shouldRefresh: StateFlow<Boolean> = _shouldRefresh.asStateFlow()

    fun setSelectedCoordinate(coordinate: Coordinate){
        _selectedCoordinate.value = coordinate
    }

    fun setShouldRefresh(value: Boolean){
        _shouldRefresh.value = value
    }

}