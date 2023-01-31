package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.viewmodel.EventViewModel
import java.util.*

@Composable
fun EventFeed(
    onEventClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventViewModel = viewModel()
) {
    val filters: SnapshotStateList<Filter> = remember { viewModel.getFilters() }
    EventFeed(
        filters = filters,
        onEventClick = onEventClick,
        modifier = modifier
    )
}

@Composable
private fun EventFeed(
    filters: SnapshotStateList<Filter>,
    onEventClick: (UUID) -> Unit,
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
    onEventClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventViewModel = viewModel()
) {
    var filterPageVisible by rememberSaveable { mutableStateOf(false) }
    val events = viewModel.events.observeAsState().value ?: remember { mutableStateListOf() }
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
                    val date = getCurrentDateTime()
                    viewModel.getEventsByDateAndType(date, filter.eventType)
                    events.clear()
                    events.addAll(events)
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
            EmptyStateMessage(
                selectedType = selectedType,
                events = events
            )
        }
    }
}

@Composable
fun EmptyStateMessage(
    selectedType: MutableState<EventType>,
    events: SnapshotStateList<Event>
) {
    val eventType = when (selectedType.value) {
        EventType.HappyHour -> "Happy Hour"
        EventType.Brunch -> "Brunch"
        EventType.Sports -> "Sports"
        EventType.Special -> "Special"
        else -> ""
    }
    val emptyEventMessage = buildAnnotatedString {
        append("Sorry, there are no ")
        withStyle(style = SpanStyle(HermosaHappyHourTheme.colors.brand)) {
            append(eventType)
        }
        append(" events today.")
    }
    if (events.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = emptyEventMessage,
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

@Preview
@Composable
private fun EventFeedPreview() {
    HermosaHappyHourTheme {
        EventFeed(onEventClick = {})
    }
}