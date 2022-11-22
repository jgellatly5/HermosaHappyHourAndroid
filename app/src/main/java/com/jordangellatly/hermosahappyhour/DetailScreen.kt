package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailScreen(
    navController: NavController,
    name: String
) {
    val restaurantViewModel: RestaurantViewModel = viewModel()
    val restaurant = restaurantViewModel.sampleRestaurantData.find { it.name == name }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            Box {
                Image(
                    painter = painterResource(
                        id = restaurant?.image ?: R.drawable.tower12
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
                TopAppBar(
                    elevation = 0.dp,
                    title = { Text(text = "") },
                    backgroundColor = Color.Transparent,
                    navigationIcon = if (navController.previousBackStackEntry != null) {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        }
                    } else {
                        null
                    }
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = restaurant?.description ?: "",
                    style = MaterialTheme.typography.body2
                )
            }
            val location = restaurant?.location ?: Location(0.0, 0.0)
            val latlng = LatLng(location.latitude, location.longitude)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(latlng, 10f)
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = latlng),
                    title = name
                )
            }
            Hours(restaurant = restaurant)
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        navController = rememberNavController(),
        name = "Baja Sharkeez"
    )
}

@Composable
fun Hours(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        DayOfWeek.values().forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.toString(),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.h5,
            )
            val businessHours = restaurant?.businessHours?.get(dayOfWeek)
            val happyHour = restaurant?.happyHour?.get(dayOfWeek)
            Text(
                text = "Business Hours: $businessHours",
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = "Happy Hour: $happyHour",
                style = MaterialTheme.typography.h6,
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoursPreview() {
    Hours(
        restaurant = Restaurant(
            "Baja Sharkeez",
            "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
            R.drawable.sharkeez,
            Location(
                latitude = 33.861988,
                longitude = -118.40071
            ),
            mapOf(
                Pair(DayOfWeek.SUNDAY, "9AM - 2AM"),
                Pair(DayOfWeek.MONDAY, "11AM - 2AM"),
                Pair(DayOfWeek.TUESDAY, "11AM - 2AM"),
                Pair(DayOfWeek.WEDNESDAY, "11AM - 2AM"),
                Pair(DayOfWeek.THURSDAY, "11AM - 2AM"),
                Pair(DayOfWeek.FRIDAY, "11AM - 2AM"),
                Pair(DayOfWeek.SATURDAY, "11AM - 2AM")
            ),
            mapOf(
                Pair(DayOfWeek.SUNDAY, "N/A"),
                Pair(DayOfWeek.MONDAY, "3PM - 7PM"),
                Pair(DayOfWeek.TUESDAY, "3PM - 7PM"),
                Pair(DayOfWeek.WEDNESDAY, "3PM - 7PM"),
                Pair(DayOfWeek.THURSDAY, "3PM - 7PM"),
                Pair(DayOfWeek.FRIDAY, "3PM - 7PM"),
                Pair(DayOfWeek.SATURDAY, "N/A")
            )
        )
    )
}