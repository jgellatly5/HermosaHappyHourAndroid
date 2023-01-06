package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventRepo
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventFeed(
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters: SnapshotStateList<Filter> = remember { EventRepo.getFilters() }
    EventFeed(
        filters = filters,
        onEventClick = onEventClick,
        modifier = modifier
    )
}

@Composable
private fun EventFeed(
    filters: SnapshotStateList<Filter>,
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
    filters: SnapshotStateList<Filter>,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val date = getCurrentDateTime()
    var filterPageVisible by rememberSaveable { mutableStateOf(false) }
    val events = remember { EventRepo.getAllEventsByDate(date) }
    Box(modifier) {
        Column {
            Spacer(
                modifier = Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            val selectedType = remember { mutableStateOf(EventType.HappyHour) }
            FilterBar(
                filters = filters,
                selectedType = selectedType,
                onFilterClick = { filter ->
                    val filteredEvents = if (filter.eventType == EventType.All) {
                        EventRepo.getAllEventsByDate(date)
                    } else {
                        EventRepo.getEventsByDateAndType(date, filter.eventType)
                    }
                    events.clear()
                    events.addAll(filteredEvents)
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
            val eventType = when (selectedType.value) {
                EventType.HappyHour -> "Happy Hour"
                EventType.Brunch -> "Brunch"
                EventType.Sports -> "Sports"
                EventType.Special -> "Special"
                else -> ""
            }
            if (events.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sorry, there are no $eventType events today.",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.subtitle1,
                        color = HermosaHappyHourTheme.colors.textSecondary,
                        modifier = Modifier
                            .width(300.dp)
                            .padding(start = 16.dp, end = 16.dp)
                    )
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