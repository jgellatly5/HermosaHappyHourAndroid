package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventRepo
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.ui.components.FilterBar
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventFeed(
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    val date = getCurrentDateTime()
    val events = remember { EventRepo.getAllEventsByDate(date) }
    val filters = remember { EventRepo.getFilters() }
    EventFeed(
        events = events,
        filters = filters,
        onEventClick = onEventClick,
        modifier = modifier
    )
}

@Composable
private fun EventFeed(
    events: List<Event>,
    filters: List<Filter>,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            EventList(
                events = events,
                filters = filters,
                onEventClick = onEventClick
            )
            DateBar()
        }
    }
}

@Composable
private fun EventList(
    events: List<Event>,
    filters: List<Filter>,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var filtersVisible by rememberSaveable { mutableStateOf(false) }
    Box(modifier) {
        Column {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            FilterBar(filters, onShowFilters = { filtersVisible = true })
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ) {
                items(events) { event ->
                    EventItem(event, onEventClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun EventFeedPreview() {
    HermosaHappyHourTheme {
        EventFeed(onEventClick = { })
    }
}