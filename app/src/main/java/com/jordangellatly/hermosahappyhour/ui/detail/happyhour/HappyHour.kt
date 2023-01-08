package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getDayOfWeekFromTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HappyHour(
    weeklyHappyHour: Map<String, Event?>,
    specials: List<Deal>,
    currentTime: Calendar,
    startTime: Calendar,
    endTime: Calendar,
    stringStart: String?,
    stringEnd: String?
) {
    Column(modifier = Modifier.padding(8.dp)) {
        val weeklyHours = weeklyHappyHour
            .mapValues {
                val happyHourDayStart = it.value?.startTimestamp?.let { startTimestamp ->
                    formatTimestamp(startTimestamp, "ha")
                } ?: ""
                val happyHourDayEnd = it.value?.endTimestamp?.let { endTimestamp ->
                    formatTimestamp(endTimestamp, "ha")
                } ?: ""
                if (happyHourDayStart.isEmpty() || happyHourDayEnd.isEmpty()) {
                    "Not Available"
                } else {
                    "$happyHourDayStart - $happyHourDayEnd"
                }
            }
        HappyHourTimer(
            weeklyHours = weeklyHours,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = stringStart,
            stringEnd = stringEnd
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
    val restaurant = tower12
    val event = saturdayHappyHour
    val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val startDate = timestampFormat.parse(event.startTimestamp)
    val startTime = Calendar.getInstance().apply {
        if (startDate != null) {
            time = startDate
        }
    }
    val endDate = timestampFormat.parse(event.endTimestamp)
    val endTime = Calendar.getInstance().apply {
        if (endDate != null) {
            time = endDate
        }
    }
    val currentTime = Calendar.getInstance()
    val stringStart = formatTimestamp(event.startTimestamp, "ha")
    val stringEnd = formatTimestamp(event.endTimestamp, "ha")

    val weeklyHappyHour = restaurant.eventsByDate
        .mapKeys { it.key.getDayOfWeekFromTimestamp() }
        .mapValues { it.value[EventType.HappyHour] }
    HermosaHappyHourTheme {
        HappyHour(
            weeklyHappyHour = weeklyHappyHour,
            specials = event.specials,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = stringStart,
            stringEnd = stringEnd
        )
    }
}