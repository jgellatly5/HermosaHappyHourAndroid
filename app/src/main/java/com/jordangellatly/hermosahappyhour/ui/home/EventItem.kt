package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.model.saturdayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.detail.shared.EventHeader
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventItem(
    event: Event,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val restaurant = remember { RestaurantRepo.getRestaurant(event.restaurantId) }
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
                Box {
                    Image(
                        painter = painterResource(id = restaurant.image),
                        contentDescription = "Restaurant Image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            EventHeader(
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
            onEventClick = {}
        )
    }
}