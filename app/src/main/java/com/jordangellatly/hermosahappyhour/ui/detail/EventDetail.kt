package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.detail.brunch.Brunch
import com.jordangellatly.hermosahappyhour.ui.detail.happyhour.HappyHour
import com.jordangellatly.hermosahappyhour.ui.detail.shared.RestaurantInfo
import com.jordangellatly.hermosahappyhour.ui.detail.special.SpecialEvent
import com.jordangellatly.hermosahappyhour.ui.detail.sports.Sports
import com.jordangellatly.hermosahappyhour.ui.home.DateBar
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventDetail(
    eventId: Long,
    upPress: () -> Unit
) {
    val selectedEvent = remember(eventId) { EventRepo.getEvent(eventId) }
    val restaurant = RestaurantRepo.getRestaurant(selectedEvent.restaurantId)
    HermosaHappyHourSurface {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Header(
                restaurantName = restaurant.name,
                imageResource = restaurant.image,
                upPress = upPress
            )
            val date = getCurrentDateTime()
            val defaultFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDateTimestamp = defaultFormat.format(date)
            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            val eventList = restaurant.eventsByDate
                    .getValue(formattedDateTimestamp)
                    .map { it.value }
                    .sortedBy { if (it == selectedEvent) 0 else 1 }
            if (eventList.size > 1) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    contentColor = HermosaHappyHourTheme.colors.textPrimary,
                    backgroundColor = HermosaHappyHourTheme.colors.uiBackground
                ) {
                    eventList.forEachIndexed { index, event ->
                        Tab(
                            selected = pagerState.currentPage == index,
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                            text = {
                                Text(
                                    text = event.eventType.name,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    count = eventList.size,
                    state = pagerState,
                ) { page ->
                    EventInfo(event = eventList[page])
                }
            } else {
                EventInfo(event = selectedEvent)
            }
            RestaurantInfo(restaurant = restaurant)
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
                drinkSpecials = event.drinkSpecials
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
                drinkSpecials = event.drinkSpecials
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

@Preview
@Composable
private fun EventDetailPreview() {
    HermosaHappyHourTheme {
        EventDetail(
            eventId = 1,
            upPress = {}
        )
    }
}