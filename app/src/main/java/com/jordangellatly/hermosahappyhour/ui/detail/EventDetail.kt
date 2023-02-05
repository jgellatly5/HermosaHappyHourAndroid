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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.components.ErrorMessage
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.detail.eventinfo.EventInfo
import com.jordangellatly.hermosahappyhour.ui.detail.restaurantinfo.RestaurantInfo
import com.jordangellatly.hermosahappyhour.ui.home.DateBar
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon
import com.jordangellatly.hermosahappyhour.viewmodel.EventDetailViewModel
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventDetail(
    upPress: () -> Unit,
    viewModel: EventDetailViewModel = viewModel()
) {
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
                ErrorMessage(state.message)
            }
            is EventDetailViewModel.EventDetailUiState.Loaded -> {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Header(
                        restaurantName = state.restaurant.name,
                        imageResource = state.restaurant.image,
                        upPress = upPress
                    )
                    val pagerState = rememberPagerState()
                    val coroutineScope = rememberCoroutineScope()
                    if (state.eventList.size > 1) {
                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                            contentColor = HermosaHappyHourTheme.colors.textPrimary,
                            backgroundColor = HermosaHappyHourTheme.colors.uiBackground
                        ) {
                            state.eventList.forEachIndexed { index, _ ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                                    text = {
                                        val event = state.eventList[index]
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
                            count = state.eventList.size,
                            state = pagerState,
                        ) { page ->
                            val event = state.eventList[page]
                            EventInfo(
                                weeklyHoursDescription = event.weeklyHoursDescription,
                                eventStart = event.startTimestamp,
                                eventEnd = event.endTimestamp,
                                eventTitle = event.title,
                                eventUrl = event.eventInfoUrl,
                                drinkSpecials = event.drinkSpecials,
                                foodSpecials = event.foodSpecials
                            )
                        }
                    } else {
                        val event = state.event
                        EventInfo(
                            weeklyHoursDescription = event.weeklyHoursDescription,
                            eventStart = event.startTimestamp,
                            eventEnd = event.endTimestamp,
                            eventTitle = event.title,
                            eventUrl = event.eventInfoUrl,
                            drinkSpecials = event.drinkSpecials,
                            foodSpecials = event.foodSpecials
                        )
                    }
                    RestaurantInfo(restaurant = state.restaurant)
                }
            }
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
            upPress = {}
        )
    }
}