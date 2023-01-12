package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.wednesdayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.components.RestaurantImage
import com.jordangellatly.hermosahappyhour.ui.detail.shared.EventTimer
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun HappyHour(
    weeklyHoursDescription: String,
    eventStart: String,
    eventEnd: String,
    eventTitle: String,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        EventTimer(
            weeklyHoursDescription = weeklyHoursDescription,
            eventStart = eventStart,
            eventEnd = eventEnd,
            eventTitle = eventTitle
        )
        specials.forEach { deal ->
            DealItem(deal = deal)
        }
    }
}

@Composable
private fun DealItem(deal: Deal) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Column(modifier = Modifier.weight(3f)) {
            Text(
                text = deal.price,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
            )
            Text(
                text = deal.description,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                lineHeight = 20.sp
            )
        }
        RestaurantImage(
            imageUrl = "https://cdn-icons-png.flaticon.com/512/187/187448.png",
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .size(80.dp)
                .padding(8.dp)
        )
    }
    HappyHourDivider()
}

@Preview(showBackground = true)
@Composable
private fun HappyHourPreview() {
    val event = wednesdayHappyHour
    HermosaHappyHourTheme {
        HappyHour(
            weeklyHoursDescription = event.weeklyHoursDescription,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            eventTitle = event.title,
            specials = event.specials
        )
    }
}