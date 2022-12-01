package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jordangellatly.hermosahappyhour.ui.components.BottomNavigationBar
import com.jordangellatly.hermosahappyhour.RestaurantViewModel
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 40.dp)
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
                Divider(color = HermosaHappyHourTheme.colors.textSecondary)
            }
            item {
                Text(
                    text = "Featured Deals",
                    style = MaterialTheme.typography.h6,
                    color = HermosaHappyHourTheme.colors.textSecondary
                )
            }
            items(restaurantViewModel.sampleHomeRestaurantData) { restaurant ->
                RestaurantCard(
                    name = restaurant.name,
                    image = restaurant.image
                ) {
                    navController.navigate("detail/${restaurant.name}")
                }
            }
            item {
                Text(
                    text = "Featured Events",
                    style = MaterialTheme.typography.h6,
                    color = HermosaHappyHourTheme.colors.textSecondary
                )
            }
            items(restaurantViewModel.sampleHomeRestaurantData) { restaurant ->
                RestaurantCard(
                    name = restaurant.name,
                    image = restaurant.image
                ) {
                    navController.navigate("detail/${restaurant.name}")
                }
            }
            item {
                Text(
                    text = "Popular Restaurants",
                    style = MaterialTheme.typography.h6,
                    color = HermosaHappyHourTheme.colors.textSecondary
                )
            }
            items(restaurantViewModel.sampleHomeRestaurantData) { restaurant ->
                RestaurantCard(
                    name = restaurant.name,
                    image = restaurant.image
                ) {
                    navController.navigate("detail/${restaurant.name}")
                }
            }
            item {
                Text(
                    text = "Newly Added",
                    style = MaterialTheme.typography.h6,
                    color = HermosaHappyHourTheme.colors.textSecondary
                )
            }
            items(restaurantViewModel.sampleHomeRestaurantData) { restaurant ->
                RestaurantCard(
                    name = restaurant.name,
                    image = restaurant.image
                ) {
                    navController.navigate("detail/${restaurant.name}")
                }
            }
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