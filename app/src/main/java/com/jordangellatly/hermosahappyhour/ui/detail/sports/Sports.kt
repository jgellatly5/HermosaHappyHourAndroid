package com.jordangellatly.hermosahappyhour.ui.detail.sports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.deals.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyEventScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun Sports(
    weeklyHours: Map<String, String>,
    eventStart: String,
    eventEnd: String,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        SportsTimer(
            weeklyHours = weeklyHours,
            eventStart = eventStart,
            eventEnd = eventEnd
        )
        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Sport Event Specials",
                specials = specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SportsPreview() {
    val restaurant = tower12
    val event = sundaySportEvent
    val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Sports)
    HermosaHappyHourTheme {
        Sports(
            weeklyHours = weeklyHours,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            specials = event.specials
        )
    }
}