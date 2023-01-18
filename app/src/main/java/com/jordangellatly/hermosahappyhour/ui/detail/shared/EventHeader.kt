package com.jordangellatly.hermosahappyhour.ui.detail.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.tower12.tower12ThursdayHappyHour
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventHeader(
    weeklyHoursDescription: String,
    eventStart: String,
    eventEnd: String,
    eventTitle: String
) {
    Column {
        Text(
            text = eventTitle,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = weeklyHoursDescription,
            fontWeight = FontWeight.Bold,
            color = HermosaHappyHourTheme.colors.textSecondary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
        EventTimer(
            eventStart = eventStart,
            eventEnd = eventEnd,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EventHeaderPreview() {
    val event = tower12ThursdayHappyHour
    HermosaHappyHourTheme {
        EventHeader(
            weeklyHoursDescription = event.weeklyHoursDescription,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            eventTitle = event.title
        )
    }
}