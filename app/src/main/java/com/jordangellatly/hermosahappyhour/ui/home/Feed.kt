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
fun Feed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    val restaurantCollections = remember {
        when (eventType) {
            EventType.HappyHour -> {
                RestaurantRepo.getHappyHourRestaurants()
            }
            EventType.Default -> {
                RestaurantRepo.getRestaurantsWithEvents()
            }
            else -> {
                RestaurantRepo.getHappyHourRestaurants()
            }
        }
    }
    Feed(
        restaurantCollection = restaurantCollections,
        onRestaurantClick = onRestaurantClick,
        modifier = modifier
    )
}

enum class EventType { HappyHour, Brunch, Sports, Default }

@Composable
private fun Feed(
    restaurantCollection: RestaurantCollection,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RestaurantList(
                restaurantCollection = restaurantCollection,
                onRestaurantClick = onRestaurantClick,
                eventType = eventType
            )
            DateBar()
        }
    }
}

@Composable
private fun RestaurantList(
    restaurantCollection: RestaurantCollection,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    Box(modifier) {
        Column {
            Spacer(
                Modifier.windowInsetsTopHeight(
                    WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                )
            )
            when (eventType) {
                EventType.HappyHour -> {
                    HappyHourRestaurantCollection(
                        restaurants = restaurantCollection.restaurants,
                        onRestaurantClick = onRestaurantClick
                    )
                }
                EventType.Default -> {
                    RestaurantsWithEventsCollection(
                        restaurants = restaurantCollection.restaurants,
                        onRestaurantClick = onRestaurantClick
                    )
                }
                else -> {
                    HappyHourRestaurantCollection(
                        restaurants = restaurantCollection.restaurants,
                        onRestaurantClick = onRestaurantClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FeedPreview() {
    HermosaHappyHourTheme {
        Feed(onRestaurantClick = { })
    }
}