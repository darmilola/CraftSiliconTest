package com.assignment.craftsilicontest.presentation.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.assignment.craftsilicontest.domain.models.Coordinate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(val savedStateHandle: SavedStateHandle): ViewModel(){
    private val _selectedCoordinate = MutableStateFlow(Coordinate())

    val selectedCoordinate: StateFlow<Coordinate> = _selectedCoordinate.asStateFlow()

    fun setSelectedCoordinate(coordinate: Coordinate){
        _selectedCoordinate.value = coordinate
    }

}