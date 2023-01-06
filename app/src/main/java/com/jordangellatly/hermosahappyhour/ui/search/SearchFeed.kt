package com.jordangellatly.hermosahappyhour.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun SearchFeed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    val restaurantList = remember { RestaurantRepo.getAllRestaurants() }
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
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Column {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            SearchBar(state = textState)
            RestaurantList(
                restaurants = restaurants,
                onRestaurantClick = onRestaurantClick
            )
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