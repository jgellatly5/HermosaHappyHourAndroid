package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun Feed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    eventType: EventType = EventType.HappyHour
) {
    val restaurantList = remember { RestaurantRepo.getRestaurantsByEventType(eventType) }
    Feed(
        restaurantList = restaurantList,
        onRestaurantClick = onRestaurantClick,
        modifier = modifier
    )
}

@Composable
private fun Feed(
    restaurantList: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RestaurantList(
                restaurantList = restaurantList,
                onRestaurantClick = onRestaurantClick
            )
            DateBar()
        }
    }
}

@Composable
private fun RestaurantList(
    restaurantList: List<Restaurant>,
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
            RestaurantCollection(
                restaurants = restaurantList,
                onRestaurantClick = onRestaurantClick
            )
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