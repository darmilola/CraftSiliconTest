package com.assignment.craftsilicontest.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IndeterminateCircularProgressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(40.dp),
        color = Color.Black,
        strokeWidth = 4.dp)

}