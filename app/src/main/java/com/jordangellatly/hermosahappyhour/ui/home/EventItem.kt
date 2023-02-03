package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.tower12.saturdayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.detail.shared.EventTimer
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.util.*

@Composable
fun EventItem(
    event: Event,
    onItemClick: (UUID, UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    HappyHourCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onItemClick(event.restaurantId, event.id) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(event.eventImageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Restaurant Image",
                        placeholder = painterResource(R.drawable.tower12),
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Text(
                text = event.restaurantName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            EventTimer(
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title
            )
        }
    }
}

@Preview(heightDp = 240)
@Composable
private fun EventItemPreview() {
    HermosaHappyHourTheme {
        val event = saturdayHappyHour
        EventItem(
            event = event,
            onItemClick = { _, _ -> }
        )
    }
}