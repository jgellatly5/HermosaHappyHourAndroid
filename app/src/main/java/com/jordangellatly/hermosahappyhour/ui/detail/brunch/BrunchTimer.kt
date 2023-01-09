package com.jordangellatly.hermosahappyhour.ui.detail.brunch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.sundayBrunch
import com.jordangellatly.hermosahappyhour.model.tower12
import com.jordangellatly.hermosahappyhour.ui.detail.EventCountdown
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyEventScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.detail.popup.HoursPopup
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun BrunchTimer(
    weeklyHours: Map<String, String>,
    eventStart: String,
    eventEnd: String
) {
    var popupControl by remember { mutableStateOf(false) }
    Column {
        Text(
            text = "Brunch",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        EventCountdown(
            eventStart = eventStart,
            eventEnd = eventEnd,
        )
        Text(
            text = "See all brunch hours \u279E",
            fontWeight = FontWeight.Bold,
            color = HermosaHappyHourTheme.colors.brand,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .clickable(onClick = { popupControl = true })
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        if (popupControl) {
            HoursPopup(
                title = "Brunch",
                weeklyHours = weeklyHours,
                onClick = { popupControl = false },
                onDismissRequest = { popupControl = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BrunchTimerPreview() {
    val restaurant = tower12
    val event = sundayBrunch
    val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.HappyHour)
    HermosaHappyHourTheme {
        BrunchTimer(
            weeklyHours = weeklyHours,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp
        )
    }
}