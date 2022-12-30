package com.jordangellatly.hermosahappyhour.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.home.DateBar
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun SearchFeed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    val restaurantList = remember { RestaurantRepo.getRestaurantsByEventType(eventType) }
    SearchFeed(
        restaurants = restaurantList,
        onRestaurantClick = onRestaurantClick,
        modifier = modifier
    )
}

@Composable
private fun SearchFeed(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RestaurantList(
                restaurants = restaurants,
                onRestaurantClick = onRestaurantClick
            )
            DateBar()
        }
    }
}

@Composable
private fun RestaurantList(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Column {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ) {
                items(restaurants) { restaurant ->
                    RestaurantItem(restaurant, onRestaurantClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun RestaurantFeedPreview() {
    HermosaHappyHourTheme {
        SearchFeed(onRestaurantClick = { })
    }
}