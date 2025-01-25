package com.assignment.craftsilicontest.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
public fun ButtonComponent(modifier: Modifier, buttonText: String, borderStroke: BorderStroke?, shape: Shape, textColor: Color, fontSize: Int, style: TextStyle, onClick: ()-> Unit) {
    Button(
        onClick = {
            onClick()
        },
        border = borderStroke,
        shape = shape,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ){
        TextComponent(
            text = buttonText, fontSize = fontSize, textStyle = style, textColor = textColor, textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold)
    }
}