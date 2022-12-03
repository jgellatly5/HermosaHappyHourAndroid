package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jordangellatly.hermosahappyhour.model.RestaurantViewModel
import com.jordangellatly.hermosahappyhour.ui.components.BottomNavigationBar
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController
) {
    val restaurantViewModel: RestaurantViewModel = viewModel()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        backgroundColor = HermosaHappyHourTheme.colors.uiBackground
    ) { contentPadding ->
        val scroll = rememberScrollState(0)
        val gradient = when ((0 / 2) % 2) {
            0 -> HermosaHappyHourTheme.colors.gradient6_1
            else -> HermosaHappyHourTheme.colors.gradient6_2
        }
        // The Cards show a gradient which spans 3 cards and scrolls with parallax.
        val gradientWidth = with(LocalDensity.current) {
            (6 * (HighlightCardWidth + HighlightCardPadding).toPx())
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val date = getCurrentDateTime()
                val dateInString = date.toString("EEEE, MMM d, yyyy")
                Text(
                    text = dateInString,
                    style = MaterialTheme.typography.h5,
                    color = HermosaHappyHourTheme.colors.textSecondary
                )
            }
            Divider(
                color = HermosaHappyHourTheme.colors.uiBorder,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "Featured Deals",
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(restaurantViewModel.sampleSearchRestaurantData) { index, restaurant ->
                    HighlightRestaurantItem(
                        restaurant = restaurant,
                        onRestaurantClick = {
                            navController.navigate("detail/${restaurant.name}")
                        },
                        index = index,
                        gradient = gradient,
                        gradientWidth = gradientWidth,
                        scroll = scroll.value
                    )
                }
            }
            Divider(
                color = HermosaHappyHourTheme.colors.uiBorder,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "Featured Events",
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(restaurantViewModel.sampleSearchRestaurantData) { index, restaurant ->
                    HighlightRestaurantItem(
                        restaurant = restaurant,
                        onRestaurantClick = {
                            navController.navigate("detail/${restaurant.name}")
                        },
                        index = index,
                        gradient = gradient,
                        gradientWidth = gradientWidth,
                        scroll = scroll.value
                    )
                }
            }
            Divider(
                color = HermosaHappyHourTheme.colors.uiBorder,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "Popular Restaurants",
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(restaurantViewModel.sampleSearchRestaurantData) { index, restaurant ->
                    HighlightRestaurantItem(
                        restaurant = restaurant,
                        onRestaurantClick = {
                            navController.navigate("detail/${restaurant.name}")
                        },
                        index = index,
                        gradient = gradient,
                        gradientWidth = gradientWidth,
                        scroll = scroll.value
                    )
                }
            }
            Divider(
                color = HermosaHappyHourTheme.colors.uiBorder,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = "Newly Added",
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                itemsIndexed(restaurantViewModel.sampleSearchRestaurantData) { index, restaurant ->
                    HighlightRestaurantItem(
                        restaurant = restaurant,
                        onRestaurantClick = {
                            navController.navigate("detail/${restaurant.name}")
                        },
                        index = index,
                        gradient = gradient,
                        gradientWidth = gradientWidth,
                        scroll = scroll.value
                    )
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date = Calendar.getInstance().time

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HermosaHappyHourTheme {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}