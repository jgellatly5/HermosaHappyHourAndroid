package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.sampleSearchRestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.components.RestaurantImage
import com.jordangellatly.hermosahappyhour.ui.components.offsetGradientBackground
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
private fun FeaturedRestaurants(
    index: Int,
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
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
        itemsIndexed(restaurants) { index, snack ->
            FeaturedDealsItem(
                snack,
                onRestaurantClick,
                index,
                gradient,
                gradientWidth,
                scroll.value
            )
        }
    }
}

@Preview
@Composable
fun FeaturedRestaurantsPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        FeaturedRestaurants(
            index = 0,
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun FeaturedDealsItem(
    restaurant: Restaurant,
    onRestaurantClick: (Long) -> Unit,
    index: Int,
    gradient: List<Color>,
    gradientWidth: Float,
    scroll: Int,
    modifier: Modifier = Modifier
) {
    val left = index * with(LocalDensity.current) {
        (HighlightCardWidth + HighlightCardPadding).toPx()
    }
    val date = getCurrentDateTime()
    val dateInString = date.toString("EEEE").uppercase()
    val dailyInfo = restaurant.hoursAndSpecials.find { it.dayOfWeek.toString() == dateInString }
    val happyHourInfo = dailyInfo?.specialEvents?.find { it.title == "Happy Hour" }
    val happyHours = happyHourInfo?.hours
    HappyHourCard(
        modifier = modifier
            .size(
                width = 340.dp,
                height = 400.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = { onRestaurantClick(restaurant.id) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                val gradientOffset = left - (scroll / 3f)
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .offsetGradientBackground(gradient, gradientWidth, gradientOffset)
                )
                RestaurantImage(
                    imageUrl = restaurant.companyLogoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = happyHourInfo?.title.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = happyHours.toString(),
                style = MaterialTheme.typography.body1,
                color = HermosaHappyHourTheme.colors.textHelp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column {
                happyHourInfo?.specials?.forEach { deal ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = deal.price,
                            style = MaterialTheme.typography.caption,
                            color = HermosaHappyHourTheme.colors.brand,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .width(80.dp)
                        )
                        Text(
                            text = deal.description,
                            style = MaterialTheme.typography.caption,
                            color = HermosaHappyHourTheme.colors.textSecondary,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FeaturedDealsItemPreview() {
    HermosaHappyHourTheme {
        val restaurant = sampleSearchRestaurantData.first()
        FeaturedDealsItem(
            restaurant = restaurant,
            onRestaurantClick = {},
            index = 0,
            gradient = HermosaHappyHourTheme.colors.gradient6_1,
            gradientWidth = gradientWidth,
            scroll = 0
        )
    }
}