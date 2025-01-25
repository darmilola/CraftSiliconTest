package com.assignment.craftsilicontest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.assignment.craftsilicontest.component.TextComponent
import com.assignment.craftsilicontest.di.initKoin
import com.assignment.craftsilicontest.ui.theme.Purple40
import kotlinx.coroutines.delay

class SplashScreen(): Screen {
    @Composable
    override fun Content() {
        initKoin()
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.Yellow), contentAlignment = Alignment.Center) {

            TextComponent(
                text = "Weather App",
                fontSize = 30,
                textStyle = MaterialTheme.typography.titleLarge,
                textColor = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )

        }

        LaunchedEffect(key1 = true) {
            delay(3000L)
            navigator.replaceAll(MainScreen())
        }
    }
}