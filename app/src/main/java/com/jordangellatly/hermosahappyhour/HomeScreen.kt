package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val date = getCurrentDateTime()
                    val dateInString = date.toString("EEEE, MMM d, yyyy")
                    Text(
                        text = dateInString,
                        style = MaterialTheme.typography.h5
                    )
                }
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