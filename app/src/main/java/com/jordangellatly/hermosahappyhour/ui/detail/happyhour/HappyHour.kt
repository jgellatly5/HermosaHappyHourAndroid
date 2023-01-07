package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.getDayOfWeekFromTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HappyHour(
    weeklyEvents: Map<String, Map<EventType, Event>>
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val formattedDateTimestamp = dateFormat.format(date)
        val happyHourEvent =
            weeklyEvents.getValue(formattedDateTimestamp).getValue(EventType.HappyHour)

        val specials = happyHourEvent.specials

        val startDate = timestampFormat.parse(happyHourEvent.startTimestamp)
        val startTime = Calendar.getInstance().apply {
            if (startDate != null) {
                time = startDate
            }
        }

        val endDate = timestampFormat.parse(happyHourEvent.endTimestamp)
        val endTime = Calendar.getInstance().apply {
            if (endDate != null) {
                time = endDate
            }
        }

        val currentTime = Calendar.getInstance()

        val formattedStart = formatTimestamp(happyHourEvent.startTimestamp, "ha")
        val formattedEnd = formatTimestamp(happyHourEvent.endTimestamp, "ha")

        val weeklyHappyHour = weeklyEvents
            .mapKeys { it.key.getDayOfWeekFromTimestamp() }
            .mapValues { it.value[EventType.HappyHour] }

        HappyHourTimer(
            weeklyHappyHour = weeklyHappyHour,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = formattedStart,
            stringEnd = formattedEnd
        )

        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Happy Hour Specials",
                specials = specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HappyHourPreview() {
    HermosaHappyHourTheme {
        HappyHour(
            weeklyEvents = eventsByDateForTesting
        )
    }
}