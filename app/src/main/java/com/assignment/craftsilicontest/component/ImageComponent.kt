package com.assignment.craftsilicontest.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageComponent(modifier: Modifier, imageRes: Int, colorFilter: ColorFilter? = null, contentScale: ContentScale = ContentScale.Crop) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Image Component",
            contentScale = contentScale,
            modifier = modifier,
            colorFilter = colorFilter
        )
}
