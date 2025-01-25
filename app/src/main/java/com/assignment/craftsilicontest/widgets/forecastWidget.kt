package com.assignment.craftsilicontest.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.assignment.craftsilicontest.domain.models.Forecast
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ForecastWidget(forecast: Forecast) {
    Box(modifier = Modifier.fillMaxHeight().width(100.dp).padding(10.dp).
    background(color = Color.Transparent).
    border(border = BorderStroke(width = 2.dp, color = Color.Black), shape = RoundedCornerShape(10.dp))){
        Column(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.BottomCenter){
                TextComponent(
                    text = forecast.main!!.temperature.toString()+"°",
                    fontSize = 15,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Black,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.BottomCenter){
                ImageComponent(
                    modifier = Modifier.size(30.dp),
                    imageRes = R.drawable.wind_icon,
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
            val simpleDateFormat = SimpleDateFormat("dd MMMM", Locale.ENGLISH)

            Box(modifier = Modifier.weight(1f).fillMaxHeight(), contentAlignment = Alignment.TopCenter){
                TextComponent(
                    text = simpleDateFormat.format(forecast.date * 1000L),
                    fontSize = 15,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Black,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal
                )
            }

        }
    }
}