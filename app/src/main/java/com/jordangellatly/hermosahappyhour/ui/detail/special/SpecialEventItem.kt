package com.jordangellatly.hermosahappyhour.ui.detail.special

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.sundaySilentDiscoSunset
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.components.RestaurantImage
import com.jordangellatly.hermosahappyhour.ui.components.offsetGradientBackground
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

val HighlightCardWidth = 170.dp
val HighlightCardPadding = 16.dp

// The Cards show a gradient which spans 3 cards and scrolls with parallax.
val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

@Composable
fun SpecialEventItem(
    event: Event,
    onEventClick: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientWidth: Float,
    scroll: Int,
    modifier: Modifier = Modifier
) {
    val left = index * with(LocalDensity.current) {
        (HighlightCardWidth + HighlightCardPadding).toPx()
    }
    HappyHourCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onEventClick(event.id) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = left - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientBackground(gradient, gradientWidth, gradientOffset)
                )
                RestaurantImage(
                    imageUrl = event.image.toString(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = event.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            val formattedStart = formatTimestamp(event.startTimestamp, "ha") ?: ""
            val formattedEnd = formatTimestamp(event.endTimestamp, "ha") ?: ""

            val hours = if (formattedStart.isEmpty() || formattedEnd.isEmpty()) {
                "Not Available"
            } else {
                "$formattedStart - $formattedEnd"
            }

            Text(
                text = hours,
                style = MaterialTheme.typography.body1,
                color = HermosaHappyHourTheme.colors.textHelp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.body1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(heightDp = 260)
@Composable
fun SpecialEventItemPreview() {
    HermosaHappyHourTheme {
        SpecialEventItem(
            event = sundaySilentDiscoSunset,
            onEventClick = {},
            index = 0,
            gradient = HermosaHappyHourTheme.colors.gradient6_1,
            gradientWidth = gradientWidth,
            scroll = 0
        )
    }
}