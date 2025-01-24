package com.assignment.craftsilicontest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.assignment.craftsilicontest.R
import com.assignment.craftsilicontest.component.ImageComponent
import com.assignment.craftsilicontest.component.TextComponent
import com.assignment.craftsilicontest.ui.theme.CraftSiliconTestTheme
import com.assignment.craftsilicontest.ui.theme.Purple40
import com.assignment.craftsilicontest.ui.theme.Purple80
import com.assignment.craftsilicontest.ui.theme.Yellow
import com.assignment.craftsilicontest.widgets.ForecastWidget
import com.assignment.craftsilicontest.widgets.WeatherWidget
import kotlinx.coroutines.delay


class MainScreen(): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val weeklyForecastList = arrayListOf<Int>()
        weeklyForecastList.add(1)
        weeklyForecastList.add(2)
        weeklyForecastList.add(3)
        weeklyForecastList.add(4)
        weeklyForecastList.add(5)


        CraftSiliconTestTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                    Row(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.Yellow)){
                        Box(modifier = Modifier.fillMaxHeight().weight(1f).background(color = Color.Yellow), contentAlignment = Alignment.Center){
                            ImageComponent(
                                modifier = Modifier.size(30.dp).clickable {
                                    navigator.push(SearchScreen())
                                },
                                imageRes = R.drawable.search_icon,
                                colorFilter = ColorFilter.tint(Color.Black)
                            )
                        }
                        Box(modifier = Modifier.fillMaxHeight().weight(3f).background(color = Color.Yellow), contentAlignment = Alignment.Center){
                            TextComponent(
                                text = "Sydney",
                                fontSize = 30,
                                textStyle = MaterialTheme.typography.titleLarge,
                                textColor = Color.Black,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(modifier = Modifier.fillMaxHeight().weight(1f).background(color = Color.Yellow))
                    }



                    Column (modifier = Modifier.fillMaxWidth().weight(3.5f).background(color = Color.Red)){

                        Column(modifier = Modifier.fillMaxWidth().weight(1.5f).background(color = Color.Green)){
                            Box(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.Yellow), contentAlignment = Alignment.Center){
                                  Box(modifier = Modifier.wrapContentSize().clip(CircleShape).background(color = Color.Black).padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)){
                                      TextComponent(
                                          text = "Friday, 20 January",
                                          fontSize = 16,
                                          textStyle = MaterialTheme.typography.titleSmall,
                                          textColor = Color.White,
                                          textAlign = TextAlign.Start,
                                          fontWeight = FontWeight.Normal
                                      )
                                  }
                            }
                            Box(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.Yellow), contentAlignment = Alignment.Center){
                                TextComponent(
                                    text = "Sunny",
                                    fontSize = 20,
                                    textStyle = MaterialTheme.typography.titleSmall,
                                    textColor = Color.Black,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Medium
                                )

                            }
                        }

                        Box(modifier = Modifier.fillMaxWidth().weight(2.5f).background(color = Color.Yellow), contentAlignment = Alignment.Center){
                            TextComponent(
                                text = "17°",
                                fontSize = 200,
                                textStyle = MaterialTheme.typography.titleMedium,
                                textColor = Color.Black,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }


                    Column(modifier = Modifier.fillMaxWidth().weight(2.3f).background(color = Color.Yellow).padding(start = 30.dp, end = 30.dp)){

                        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                            TextComponent(
                                text = "Daily Summary",
                                fontSize = 20,
                                textStyle = MaterialTheme.typography.titleLarge,
                                textColor = Color.Black,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(modifier = Modifier.fillMaxWidth().weight(4f)) {
                            WeatherWidget()
                        }

                    }


                    Column(modifier = Modifier.fillMaxWidth().weight(2.2f).background(color = Color.Yellow).padding(start = 30.dp, end = 30.dp, top = 10.dp)){
                        Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.CenterStart) {
                            TextComponent(
                                text = "Weekly forecast",
                                fontSize = 20,
                                textStyle = MaterialTheme.typography.titleLarge,
                                textColor = Color.Black,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth().weight(4f).background(color = Color.Yellow)) {
                            LazyRow(modifier = Modifier.padding( top = 10.dp).fillMaxSize().background(color = Color.Yellow)) {
                                items(items = weeklyForecastList) { item ->
                                    ForecastWidget()
                                }
                            }
                        }
                    }


                }

            }
        }

    }
}