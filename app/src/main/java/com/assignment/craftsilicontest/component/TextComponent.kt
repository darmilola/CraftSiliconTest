package com.assignment.craftsilicontest.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp

@Composable
fun TextComponent(text: String, fontSize: Int, textStyle: TextStyle, textColor: Color, textAlign: TextAlign, fontWeight: FontWeight, fontFamily: FontFamily? = null, lineHeight: Int = 10, maxLines: Int = 10, overflow: TextOverflow = TextOverflow.Clip, letterSpacing: TextUnit = TextUnit.Unspecified) {
    Text(text, fontSize = fontSize.sp, fontFamily = fontFamily, style = textStyle, color = textColor, textAlign = textAlign,fontWeight = fontWeight, lineHeight = lineHeight.sp, maxLines = maxLines, overflow = overflow, letterSpacing = letterSpacing)
}

@Composable
fun TextFieldComponent(text: String, modifier: Modifier,onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
placeholderText: String, onFocusChange: (Boolean) -> Unit, placeholderTextSize: Float = 18f, maxLines: Int = 1) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()
    if (isFocused.value){
        onFocusChange(true)
    }else{
        onFocusChange(false)
    }


    BasicTextField(value = text, modifier = modifier, keyboardOptions = keyboardOptions, onValueChange = onValueChange, interactionSource = interactionSource, maxLines = maxLines, decorationBox = { innerTextField ->
        Row(modifier = Modifier.fillMaxWidth()) {
            if (text.trim().isEmpty()) {
                PlaceholderTextComponent(placeholderText, textColor = Color.Gray, textSize = placeholderTextSize)
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
            innerTextField()
        }
    })
}

@Composable
fun PlaceholderTextComponent(placeholderTile: String, textColor: Color = Color.LightGray, textSize: Float = 18f) {
    val textStyle = TextStyle(
        fontSize = TextUnit(textSize, TextUnitType.Sp),
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Normal
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        TextComponent(
            text = placeholderTile,
            fontSize = textSize.toInt(),
            textStyle = textStyle,
            textColor = textColor,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal
        )
    }
}
