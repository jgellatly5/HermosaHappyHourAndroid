package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.model.tower12.tower12MondayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.ErrorDialog
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.detail.brunch.Brunch
import com.jordangellatly.hermosahappyhour.ui.detail.happyhour.HappyHour
import com.jordangellatly.hermosahappyhour.ui.detail.special.SpecialEvent
import com.jordangellatly.hermosahappyhour.ui.detail.sports.Sports
import com.jordangellatly.hermosahappyhour.ui.home.DateBar
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon
import com.jordangellatly.hermosahappyhour.viewmodel.EventDetailViewModel
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventDetail(
    eventId: UUID,
    upPress: () -> Unit,
    viewModel: EventDetailViewModel = viewModel()
) {
//    val restaurant = RestaurantRepo.getRestaurant(selectedEvent.restaurantId)
    HermosaHappyHourSurface {
        when (val state = viewModel.uiState.collectAsState().value) {
            is EventDetailViewModel.EventDetailUiState.Empty -> {
                EmptyStateMessage()
            }
            is EventDetailViewModel.EventDetailUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            is EventDetailViewModel.EventDetailUiState.Error -> {
                ErrorDialog(state.message)
            }
            is EventDetailViewModel.EventDetailUiState.Loaded -> {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                    Header(
//                        restaurantName = restaurant.name,
//                        imageResource = restaurant.image,
//                        upPress = upPress
//                    )
//                    val date = getCurrentDateTime()
//                    val defaultFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//                    val formattedDateTimestamp = defaultFormat.format(date)
//                    val pagerState = rememberPagerState()
//                    val coroutineScope = rememberCoroutineScope()
//                    val eventList = restaurant.eventsByDate
//                        .getValue(formattedDateTimestamp)
//                        .map { it.value }
//                        .sortedBy { if (viewModel.getEventById(it) == state.event) 0 else 1 }
//                    if (eventList.size > 1) {
//                        TabRow(
//                            selectedTabIndex = pagerState.currentPage,
//                            contentColor = HermosaHappyHourTheme.colors.textPrimary,
//                            backgroundColor = HermosaHappyHourTheme.colors.uiBackground
//                        ) {
//                            eventList.forEachIndexed { index, eventId ->
//                                Tab(
//                                    selected = pagerState.currentPage == index,
//                                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
//                                    text = {
//                                        val event = viewModel.getEventById(eventId)
//                                        Text(
//                                            text = event.eventType.name,
//                                            maxLines = 2,
//                                            overflow = TextOverflow.Ellipsis,
//                                        )
//                                    }
//                                )
//                            }
//                        }
//                        HorizontalPager(
//                            count = eventList.size,
//                            state = pagerState,
//                        ) { page ->
//                            EventInfo(event = viewModel.getEventById(eventList[page]))
//                        }
//                    } else {
//                        EventInfo(event = state.event)
//                    }
//                    RestaurantInfo(restaurant = restaurant)
                }
            }
        }
    }
}

@Composable
private fun EventInfo(event: Event) {
    when (event.eventType) {
        EventType.HappyHour -> {
            HappyHour(
                weeklyHoursDescription = event.weeklyHoursDescription,
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title,
                eventUrl = event.eventUrl,
                drinkSpecials = event.drinkSpecials,
                foodSpecials = event.foodSpecials
            )
        }
        EventType.Brunch -> {
            Brunch(
                weeklyHoursDescription = event.weeklyHoursDescription,
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title,
                specials = event.drinkSpecials
            )
        }
        EventType.Sports -> {
            Sports(
                weeklyHoursDescription = event.weeklyHoursDescription,
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title,
                specials = event.drinkSpecials
            )
        }
        EventType.Special -> {
            SpecialEvent(
                weeklyHoursDescription = event.weeklyHoursDescription,
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title,
                specials = event.drinkSpecials
            )
        }
        else -> {
            HappyHour(
                weeklyHoursDescription = event.weeklyHoursDescription,
                eventStart = event.startTimestamp,
                eventEnd = event.endTimestamp,
                eventTitle = event.title,
                eventUrl = event.eventUrl,
                drinkSpecials = event.drinkSpecials,
                foodSpecials = event.foodSpecials
            )
        }
    }
}

@Composable
private fun Header(
    restaurantName: String,
    imageResource: Int,
    upPress: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Restaurant Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        TopAppBar(
            elevation = 0.dp,
            title = { Text(text = "") },
            backgroundColor = Color.Transparent
        )
        DateBar()
        Up(upPress = upPress)
        Text(
            text = restaurantName,
            style = MaterialTheme.typography.h4,
            color = HermosaHappyHourTheme.colors.textInteractive,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = HermosaHappyHourTheme.colors.iconInteractive,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
private fun EmptyStateMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry, we could not find that event.",
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
private fun EventDetailPreview() {
    HermosaHappyHourTheme {
        EventDetail(
            eventId = tower12MondayHappyHour.id,
            upPress = {}
        )
    }
}