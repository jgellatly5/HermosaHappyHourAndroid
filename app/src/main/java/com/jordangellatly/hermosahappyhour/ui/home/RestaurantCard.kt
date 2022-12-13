package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.CollectionType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantCollection
import com.jordangellatly.hermosahappyhour.model.sampleSearchRestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.components.offsetGradientBackground
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringIcon

val HighlightCardWidth = 170.dp
val HighlightCardPadding = 16.dp

// The Cards show a gradient which spans 3 cards and scrolls with parallax.
private val gradientWidth
    @Composable
    get() = with(LocalDensity.current) {
        (3 * (HighlightCardWidth + HighlightCardPadding).toPx())
    }

@Composable
fun RestaurantCollection(
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
            CollectionType.Highlight -> {
                HighlightedRestaurants(index, restaurantCollection.restaurants, onRestaurantClick)
            }
            CollectionType.Normal -> {
                Restaurants(restaurantCollection.restaurants, onRestaurantClick)
            }
        }
    }
}

@Preview
@Composable
fun RestaurantCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurantCollection = RestaurantCollection(
            id = 1,
            name = "Featured Deals",
            restaurants = sampleSearchRestaurantData,
            type = CollectionType.Featured
        )
        RestaurantCollection(
            restaurantCollection = restaurantCollection,
            onRestaurantClick = {}
        )
    }
}

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
private fun HighlightedRestaurants(
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
            HighlightRestaurantItem(
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
fun HighlightedRestaurantsPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        HighlightedRestaurants(
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

@Composable
fun HighlightRestaurantItem(
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
                width = 170.dp,
                height = 250.dp
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
        }
    }
}

@Preview
@Composable
fun HighlightRestaurantItemPreview() {
    HermosaHappyHourTheme {
        val restaurant = sampleSearchRestaurantData.first()
        HighlightRestaurantItem(
            restaurant = restaurant,
            onRestaurantClick = {},
            index = 0,
            gradient = HermosaHappyHourTheme.colors.gradient6_1,
            gradientWidth = gradientWidth,
            scroll = 0
        )
    }
}

@Composable
private fun Restaurants(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(restaurants) { snack ->
            RestaurantItem(snack, onRestaurantClick)
        }
    }
}

@Preview
@Composable
fun RestaurantsPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        Restaurants(
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp,
            bottom = 8.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = { onRestaurantClick(restaurant.id) })
                .padding(8.dp)
        ) {
            RestaurantImage(
                imageUrl = restaurant.companyLogoUrl,
                elevation = 4.dp,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun RestaurantItemPreview() {
    HermosaHappyHourTheme {
        val restaurant = sampleSearchRestaurantData.first()
        RestaurantItem(
            restaurant = restaurant,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun RestaurantImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    HermosaHappyHourSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(R.drawable.placeholder),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
fun RestaurantImagePreview() {
    HermosaHappyHourTheme {
        RestaurantImage(
            imageUrl = "\"https://scontent-lax3-2.xx.fbcdn.net/v/t39.30808-6/292336365_435159251951538_4078078271979326231_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=C_tAC12x4o0AX9uN-Yg&_nc_ht=scontent-lax3-2.xx&oh=00_AfAAxhYzzBx-bK8gDhRHmOeMrzUxDDsDMZeCaTyFHRr3Ug&oe=638F052F\"",
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
        )
    }
}