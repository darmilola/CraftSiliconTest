package com.assignment.craftsilicontest.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.assignment.craftsilicontest.domain.models.City

@Composable
fun CityItem(city: City, onCitySelected:(City) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(10.dp))
            .clickable {
                onCitySelected(city)
            }
            .background(color = Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxHeight()
            .weight(2f).background(color = Color.Black), contentAlignment = Alignment.Center){
            TextComponent(
                text = city.cityName,
                fontSize = 20,
                textStyle = MaterialTheme.typography.titleLarge,
                textColor = Color.Yellow,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }
        Row(modifier = Modifier.fillMaxHeight().weight(2f)
            .background(color = Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){

            ImageComponent(
                modifier = Modifier.size(24.dp).padding(end = 10.dp),
                imageRes = R.drawable.state_icon,
                colorFilter = ColorFilter.tint(Color.Yellow)
            )

               TextComponent(
                    text = city.state,
                    fontSize = 20,
                    textStyle = MaterialTheme.typography.titleSmall,
                    textColor = Color.Yellow,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                )

        }
        Row(modifier = Modifier.fillMaxHeight().weight(0.5f)
            .background(color = Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){

            ImageComponent(
                modifier = Modifier.size(24.dp),
                imageRes = R.drawable.right_arrow,
                colorFilter = ColorFilter.tint(Color.Yellow)
            )

        }

    }
}