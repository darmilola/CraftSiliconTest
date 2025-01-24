package com.assignment.craftsilicontest.widgets


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.OnBackPressed
import com.assignment.craftsilicontest.R
import com.assignment.craftsilicontest.component.ImageComponent
import com.assignment.craftsilicontest.component.TextFieldComponent

@Composable
fun SearchBarWidget(placeholderText: String, iconSize: Int, onBackPressed:() -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var borderStroke by remember { mutableStateOf(BorderStroke(2.dp, color  = Color.Gray)) }

    val modifier  = Modifier
        .padding(end = 10.dp, start = 10.dp, top = 20.dp)
        .fillMaxWidth()
        .height(50.dp)
        .border(border = borderStroke, shape = CircleShape)
        .background(color = Color.Transparent, shape = CircleShape)

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically) {

        Box(modifier = Modifier.fillMaxHeight().width(50.dp), contentAlignment = Alignment.Center){
            ImageComponent(modifier = Modifier
                .size(iconSize.dp).clickable {
                       onBackPressed()

                }, imageRes = R.drawable.back_arrow, colorFilter = ColorFilter.tint(color = Color.Black))
        }
        TextFieldComponent(
            text = text,
            modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(end = 10.dp),
            keyboardOptions = keyboardOptions,
            onValueChange = {
                text = it
                onValueChange(it)
            }, placeholderText = placeholderText, onFocusChange = { it ->
                borderStroke = if (it){
                    BorderStroke(2.dp, color  = Color.Black)
                } else{
                    BorderStroke(2.dp, color  = Color.Gray)
                }
            },
            placeholderTextSize = 17f
        )
    }
}
