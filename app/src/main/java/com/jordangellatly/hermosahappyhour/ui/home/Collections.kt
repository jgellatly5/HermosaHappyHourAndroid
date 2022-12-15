package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.CollectionType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantCollection
import com.jordangellatly.hermosahappyhour.model.sampleSearchRestaurantData
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringIcon

@Composable
fun FeaturedCollection(
    restaurantCollection: RestaurantCollection,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    index: Int = 0
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = restaurantCollection.name,
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = mirroringIcon(
                        ltrIcon = Icons.Outlined.ArrowForward,
                        rtlIcon = Icons.Outlined.ArrowBack
                    ),
                    tint = HermosaHappyHourTheme.colors.brand,
                    contentDescription = null
                )
            }
        }
        when (restaurantCollection.type) {
//            CollectionType.Featured -> {
//                FeaturedRestaurants(index, restaurantCollection.restaurants, onRestaurantClick)
//            }
            CollectionType.HappyHour -> {
                EventCollection(
                    index,
                    restaurantCollection.restaurants,
                    onRestaurantClick,
                    type = CollectionType.HappyHour
                )
            }
            CollectionType.Event -> {
                EventCollection(
                    index,
                    restaurantCollection.restaurants,
                    onRestaurantClick,
                    type = CollectionType.Event
                )
            }
            CollectionType.Normal -> {
                HappyHourCollection(restaurantCollection.restaurants, onRestaurantClick)
            }
        }
    }
}

@Preview
@Composable
fun FeaturedCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurantCollection = RestaurantCollection(
            id = 1,
            name = "Featured Happy Hours",
            restaurants = sampleSearchRestaurantData,
            type = CollectionType.Normal
        )
        FeaturedCollection(
            restaurantCollection = restaurantCollection,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun EventCollection(
    index: Int,
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    type: CollectionType = CollectionType.Normal
) {
    val scroll = rememberScrollState(0)
    val gradient = when ((index / 2) % 2) {
        0 -> HermosaHappyHourTheme.colors.gradient6_1
        else -> HermosaHappyHourTheme.colors.gradient6_2
    }
    // The Cards show a gradient which spans 3 cards and scrolls with parallax.
    val gradientWidth = with(LocalDensity.current) {
        (6 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp)
    ) {
        itemsIndexed(restaurants) { index, restaurant ->
            EventItem(
                restaurant = restaurant,
                onRestaurantClick = onRestaurantClick,
                index = index,
                gradient = gradient,
                gradientWidth = gradientWidth,
                scroll = scroll.value,
                modifier = modifier,
                type = type
            )
        }
    }
}

@Preview
@Composable
fun EventCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        EventCollection(
            index = 0,
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun HappyHourCollection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(restaurants) { snack ->
            HappyHourItem(snack, onRestaurantClick)
        }
    }
}

@Preview
@Composable
fun HappyHourCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        HappyHourCollection(
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}