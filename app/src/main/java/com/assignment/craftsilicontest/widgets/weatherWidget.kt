package com.assignment.craftsilicontest.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.assignment.craftsilicontest.R
import com.assignment.craftsilicontest.component.ImageComponent
import com.assignment.craftsilicontest.component.TextComponent
import com.assignment.craftsilicontest.domain.models.CityWeather

@Composable
fun WeatherWidget(cityWeather: CityWeather) {
    Row(
        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp))
            .background(color = Color.Blue)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().weight(1f).background(color = Color.Black).padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.BottomCenter){
                ImageComponent(
                    modifier = Modifier.size(50.dp),
                    imageRes = R.drawable.wind_icon,
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center){
                TextComponent(
                    text = cityWeather.wind?.speed.toString()+"Km/h",
                    fontSize = 20,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopCenter){
                TextComponent(
                    text = "Wind",
                    fontSize = 15,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxHeight().weight(1f).background(color = Color.Black).padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.BottomCenter){
                ImageComponent(
                    modifier = Modifier.size(50.dp),
                    imageRes = R.drawable.humidity_icon,
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center){
                TextComponent(
                    text = cityWeather.mainForecast?.humidity.toString()+"%",
                    fontSize = 20,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopCenter){
                TextComponent(
                    text = "Humidity",
                    fontSize = 15,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxHeight().weight(1f).background(color = Color.Black).padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.BottomCenter){
                ImageComponent(
                    modifier = Modifier.size(50.dp),
                    imageRes = R.drawable.visible_icon,
                    colorFilter = ColorFilter.tint(Color.Yellow)
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.Center){
                TextComponent(
                    text = cityWeather.visibility.toString()+"M",
                    fontSize = 20,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopCenter){
                TextComponent(
                    text = "Visibility",
                    fontSize = 15,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal
                )
            }

        }

    }
}