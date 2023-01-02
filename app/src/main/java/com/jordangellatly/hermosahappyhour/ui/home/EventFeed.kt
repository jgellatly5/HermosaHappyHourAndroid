package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.components.FilterBar
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventFeed(
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = remember { EventRepo.getFilters() }
    EventFeed(
        filters = filters,
        onEventClick = onEventClick,
        modifier = modifier
    )
}

@Composable
private fun EventFeed(
    filters: List<Filter>,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            EventList(
                filters = filters,
                onEventClick = onEventClick
            )
            DateBar()
        }
    }
}

@Composable
private fun EventList(
    filters: List<Filter>,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var filterPageVisible by rememberSaveable { mutableStateOf(false) }
    val events = remember { EventRepo.getAllEventsByDate(getCurrentDateTime()) }
    Box(modifier) {
        Column {
            Spacer(
                modifier = Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            FilterBar(
                filters = filters,
                onFilterClick = {
                    events.clear()
                    events.addAll(EventRepo.getEventsByType(EventType.HappyHour))
                },
                onShowFilterPopup = { filterPageVisible = true }
            )
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