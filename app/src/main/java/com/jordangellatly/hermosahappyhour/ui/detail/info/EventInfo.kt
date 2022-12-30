package com.jordangellatly.hermosahappyhour.ui.detail.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.SpecialsCollection
import com.jordangellatly.hermosahappyhour.model.tower12
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.detail.HighlightCardPadding
import com.jordangellatly.hermosahappyhour.ui.detail.HighlightCardWidth
import com.jordangellatly.hermosahappyhour.ui.detail.TodaysEventItem
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventInfo(
    weeklyEvents: Map<String, Map<EventType, Event>>
) {
    val date = getCurrentDateTime()
    val getDayFromDate = date.toString("EEEE").uppercase()
    val event = weeklyEvents
        .getValue(getDayFromDate)
        .filterKeys { it != EventType.HappyHour }
        .firstNotNullOf { it.value }
    val scroll = rememberScrollState(0)
    val gradient = when ((0 / 2) % 2) {
        0 -> HermosaHappyHourTheme.colors.gradient6_1
        else -> HermosaHappyHourTheme.colors.gradient6_2
    }
    // The Cards show a gradient which spans 3 cards and scrolls with parallax.
    val gradientWidth = with(LocalDensity.current) {
        (6 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Today's Event",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        TodaysEventItem(
            event = event,
            onEventClick = {},
            index = 0,
            gradient = gradient,
            gradientWidth = gradientWidth,
            scroll = scroll.value,
            modifier = Modifier.padding(16.dp)
        )

        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 2L,
                name = "Event Specials",
                specials = event.specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EventInfoPreview() {
    HermosaHappyHourTheme {
        EventInfo(
            weeklyEvents = tower12.eventsToday
        )
    }
}