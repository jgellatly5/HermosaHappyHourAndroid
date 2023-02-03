package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.ui.components.ErrorDialog
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.viewmodel.EventFeedViewModel
import java.util.*

@Composable
fun EventFeed(
    onItemClick: (UUID, UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventFeedViewModel = viewModel()
) {
    val filters: SnapshotStateList<Filter> = remember { viewModel.getFilters() }
    EventFeed(
        filters = filters,
        onItemClick = onItemClick,
        modifier = modifier
    )
}

@Composable
private fun EventFeed(
    filters: SnapshotStateList<Filter>,
    onItemClick: (UUID, UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            EventList(
                filters = filters,
                onItemClick = onItemClick
            )
            DateBar()
        }
    }
}

@Composable
private fun EventList(
    filters: SnapshotStateList<Filter>,
    onItemClick: (UUID, UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventFeedViewModel = viewModel()
) {
    var filterPageVisible by rememberSaveable { mutableStateOf(false) }
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
                },
                onShowFilterPopup = { filterPageVisible = true }
            )
            when (val state = viewModel.uiState.collectAsState().value) {
                is EventFeedViewModel.EventFeedUiState.Empty -> {
                    EmptyStateMessage(selectedType = selectedType)
                }
                is EventFeedViewModel.EventFeedUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is EventFeedViewModel.EventFeedUiState.Error -> {
                    ErrorDialog(state.message)
                }
                is EventFeedViewModel.EventFeedUiState.Loaded -> {
                    LazyColumn(
                        modifier = modifier,
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
                    ) {
                        items(state.events) { event ->
                            EventItem(event, onItemClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyStateMessage(selectedType: MutableState<EventType>) {
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

@Preview
@Composable
private fun EventFeedPreview() {
    HermosaHappyHourTheme {
        EventFeed(onItemClick = { _, _ ->})
    }
}