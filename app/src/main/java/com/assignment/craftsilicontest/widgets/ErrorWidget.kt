package com.assignment.craftsilicontest.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.assignment.craftsilicontest.R
import com.assignment.craftsilicontest.component.ButtonComponent
import com.assignment.craftsilicontest.component.ImageComponent
import com.assignment.craftsilicontest.component.TextComponent

@Composable
fun ErrorOccurredWidget(errorText: String, onRetryClicked: () -> Unit) {
    val columnModifier = Modifier
        .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
        .height(200.dp)
    Column(
        modifier = columnModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment  = Alignment.CenterHorizontally,
    ) {
        val modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
        Box(
            Modifier
                .clip(CircleShape)
                .size(80.dp)
                .background(color = Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            ImageComponent(modifier = Modifier
                .size(60.dp), imageRes = R.drawable.error, colorFilter = ColorFilter.tint(color = Color.Black))
        }
        TextComponent(
            text = errorText,
            fontSize = 20,
            textStyle = MaterialTheme.typography.titleLarge,
            textColor = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )

        val buttonStyle = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(0.40f)
            .background(color = Color.Transparent)
            .height(45.dp)

        ButtonComponent(modifier = buttonStyle, buttonText = "Retry", borderStroke = BorderStroke(1.dp, color = Color.DarkGray), fontSize = 16, shape = CircleShape, textColor =  Color.DarkGray, style = MaterialTheme.typography.titleLarge,
        ){
            onRetryClicked()
        }
    }

}
