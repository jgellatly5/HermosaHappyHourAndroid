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
import java.net.URI

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
            Header(
                navController = navController,
                restaurant = restaurant
            )
            Specials(restaurant = restaurant)
            GeneralInfo(restaurant = restaurant)
        }
    }
}

@Composable
fun Header(
    navController: NavController,
    restaurant: Restaurant?
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
}

@Composable
fun Specials(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = restaurant?.name.toString(),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "Next Happy Hour:",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Starts at 3PM")
            Text(text = "5:22:00 (Countdown timer)")
        }
        val drinkSpecials = restaurant?.happyHourInfo?.specials
        Text(
            text = "Today's Special(s)",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Column {
            drinkSpecials?.forEach { special ->
                Text(text = special)
            }
        }

        val specialEvent = restaurant?.dailyEvents?.get(DayOfWeek.THURSDAY).toString()
        Text(
            text = "Today's Event",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = specialEvent)
    }
}

@Composable
fun GeneralInfo(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Info",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Hours",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = "Open until 2am")
        Text(
            text = "Website",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = "https://tower12hb.com")
        Text(
            text = "Call",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = restaurant?.phoneNumber ?: "")
        Text(
            text = "Get Directions",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = restaurant?.address?.line1 ?: "")
        Text(text = restaurant?.address?.line2 ?: "")
        val location = restaurant?.location ?: Location(0.0, 0.0)
        val latlng = LatLng(location.latitude, location.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latlng, 16f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = latlng),
                title = restaurant?.name
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        navController = rememberNavController(),
        name = "Tower12"
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
            val happyHours = restaurant?.happyHours?.get(dayOfWeek)
            val happyHourInfo = restaurant?.dailyEvents?.get(dayOfWeek).toString()
            Text(
                text = "Business Hours: $businessHours",
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = "Happy Hour: $happyHours",
                style = MaterialTheme.typography.h6,
            )
            if (happyHourInfo.isNotEmpty()) {
                Text(
                    text = happyHourInfo,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HoursPreview() {
    Hours(
        restaurant = Restaurant(
            name = "Tower12",
            description = "New American classics & fancy snacks in a beachy, relaxed bar with romantic patio seating.",
            image = R.drawable.tower12,
            location = Location(
                latitude = 33.86222,
                longitude = -118.40085
            ),
            businessHours = mapOf(
                Pair(DayOfWeek.SUNDAY, "9AM - 2AM"),
                Pair(DayOfWeek.MONDAY, "11AM - 2AM"),
                Pair(DayOfWeek.TUESDAY, "11AM - 2AM"),
                Pair(DayOfWeek.WEDNESDAY, "11AM - 2AM"),
                Pair(DayOfWeek.THURSDAY, "11AM - 2AM"),
                Pair(DayOfWeek.FRIDAY, "11AM - 2AM"),
                Pair(DayOfWeek.SATURDAY, "11AM - 2AM")
            ),
            happyHours = mapOf(
                Pair(DayOfWeek.SUNDAY, "N/A"),
                Pair(DayOfWeek.MONDAY, "3PM - 7PM"),
                Pair(DayOfWeek.TUESDAY, "3PM - 7PM"),
                Pair(DayOfWeek.WEDNESDAY, "3PM - 7PM"),
                Pair(DayOfWeek.THURSDAY, "3PM - 7PM"),
                Pair(DayOfWeek.FRIDAY, "3PM - 7PM"),
                Pair(DayOfWeek.SATURDAY, "N/A")
            ),
            happyHourInfo = HappyHourInfo(
                hours = "M-F 3PM - 7PM",
                specials = listOf(
                    "Half off shots, bottled & can beers",
                    "Wines $5.50 - $7.50 ask server for selections",
                    "Add $1 to any cocktail to make it 22oz double shot",
                    "Add $1 to any draft beer to make it 32oz double size"
                )
            ),
            dailyEvents = mapOf(
                Pair(DayOfWeek.SUNDAY, ""),
                Pair(DayOfWeek.MONDAY, ""),
                Pair(DayOfWeek.TUESDAY, ""),
                Pair(DayOfWeek.WEDNESDAY, ""),
                Pair(DayOfWeek.THURSDAY, "Thursday Night Karaoke"),
                Pair(DayOfWeek.FRIDAY, ""),
                Pair(DayOfWeek.SATURDAY, "")
            ),
            specialEvent = Event(
                title = "England vs Wales",
                description = "England and Wales playing in world cup",
                image = URI("https://google.com")
            ),
            address = Address(
                line1 = "53 Pier Ave",
                line2 = "Hermosa Beach, CA 90254"
            ),
            phoneNumber = "(310) 379-6400"
        )
    )
}