package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun DealImage(
    description: String,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    HermosaHappyHourSurface(
        color = Color.White,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = description,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = HermosaHappyHourTheme.colors.textSecondary,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun DealImagePreview() {
    HermosaHappyHourTheme {
        DealImage(
            description = "Shots, bottled & can beers",
            modifier = Modifier.size(120.dp)
        )
    }
}