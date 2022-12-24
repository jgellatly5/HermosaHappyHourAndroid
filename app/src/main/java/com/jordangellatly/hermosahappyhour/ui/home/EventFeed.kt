package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.RestaurantCollection
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventFeed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val restaurantCollections = remember { RestaurantRepo.getRestaurantsWithEvents() }
    EventFeed(
        restaurantCollections,
        onRestaurantClick,
        modifier
    )
}

@Composable
private fun EventFeed(
    restaurantCollection: RestaurantCollection,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RestaurantsWithEventsList(restaurantCollection, onRestaurantClick)
            DateBar()
        }
    }
}

@Composable
private fun RestaurantsWithEventsList(
    restaurantCollection: RestaurantCollection,
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
            HappyHourRestaurantCollection(
                restaurants = restaurantCollection.restaurants,
                onRestaurantClick = onRestaurantClick
            )
        }
    }
}

@Preview
@Composable
fun EventFeedPreview() {
    HermosaHappyHourTheme {
        HappyHourFeed(onRestaurantClick = { })
    }
}