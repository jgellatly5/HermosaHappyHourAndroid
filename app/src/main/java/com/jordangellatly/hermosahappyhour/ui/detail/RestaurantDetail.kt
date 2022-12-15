package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon
import java.net.URI

@Composable
fun RestaurantDetail(
    restaurantId: Long,
    upPress: () -> Unit
) {
    val restaurant = remember(restaurantId) { RestaurantRepo.getRestaurant(restaurantId) }
    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        },
        backgroundColor = HermosaHappyHourTheme.colors.uiBackground
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            Header(restaurant = restaurant)
            Up(upPress = upPress)
            Specials(restaurant = restaurant)
            GeneralInfo(restaurant = restaurant)
        }
    }
}

@Composable
private fun Header(
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
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = HermosaHappyHourTheme.colors.iconInteractive,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
private fun Specials(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val dateInString = date.toString("EEEE").uppercase()
        val dailyInfo =
            restaurant?.hoursAndSpecials?.find { it.dayOfWeek.toString() == dateInString }
        val happyHourInfo = dailyInfo?.specialEvents?.find { it.title == "Happy Hour" }
        val happyHours = happyHourInfo?.hours
        val specials = happyHourInfo?.specials
        Text(
            text = restaurant?.name.toString(),
            style = MaterialTheme.typography.h4,
            color = HermosaHappyHourTheme.colors.textSecondary
        )
        Text(
            text = "Next Happy Hour:",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5,
            color = HermosaHappyHourTheme.colors.textSecondary
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = happyHours.toString(), color = HermosaHappyHourTheme.colors.textSecondary)
            Text(
                text = "5:22:00 (Countdown timer)",
                color = HermosaHappyHourTheme.colors.textSecondary
            )
        }

        Text(
            text = "Happy Hour Specials",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5,
            color = HermosaHappyHourTheme.colors.textSecondary
        )
        Column {
            specials?.forEach { deal ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = deal.description,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.width(200.dp)
                    )
                    Text(
                        text = deal.price,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
        }
        val specialEvent = if (happyHourInfo?.title.toString() == "Happy Hour") {
            "N/A"
        } else {
            happyHourInfo?.title.toString()
        }
        Text(
            text = "Today's Event",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = specialEvent)
        Text(
            text = "Event Specials",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = "N/A")
    }
}

@Composable
private fun GeneralInfo(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val dateInString = date.toString("EEEE").uppercase()
        val dailyInfo =
            restaurant?.hoursAndSpecials?.find { it.dayOfWeek.toString() == dateInString }
        Text(
            text = "Info",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Hours",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = dailyInfo?.businessHours.toString())
        Text(
            text = "Website",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5
        )
        Text(text = restaurant?.website.toString())
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
private fun DetailScreenPreview() {
    HermosaHappyHourTheme {
        RestaurantDetail(
            restaurantId = 1,
            upPress = {}
        )
    }
}

@Composable
private fun Hours(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        DayOfWeek.values().forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.toString(),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.h5,
            )
            val dailyInfo = restaurant?.hoursAndSpecials?.find { it.dayOfWeek == dayOfWeek }
            val businessHours = dailyInfo?.businessHours
            val happyHourInfo = dailyInfo?.specialEvents?.find { it.title == "Happy Hour" }
            val happyHours = if (happyHourInfo?.hours == null) {
                "N/A"
            } else {
                happyHourInfo.hours
            }
            Text(
                text = "Business Hours: $businessHours",
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = "Happy Hour: $happyHours",
                style = MaterialTheme.typography.h6,
            )
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HoursPreview() {
    HermosaHappyHourTheme {
        Hours(
            restaurant = Restaurant(
                id = 1,
                name = "Tower12",
                description = "New American classics & fancy snacks in a beachy, relaxed bar with romantic patio seating.",
                companyLogoUrl = "https://scontent-lax3-2.xx.fbcdn.net/v/t39.30808-6/292336365_435159251951538_4078078271979326231_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=C_tAC12x4o0AX9uN-Yg&_nc_ht=scontent-lax3-2.xx&oh=00_AfAAxhYzzBx-bK8gDhRHmOeMrzUxDDsDMZeCaTyFHRr3Ug&oe=638F052F",
                image = R.drawable.tower12,
                location = Location(
                    latitude = 33.86222,
                    longitude = -118.40085
                ),
                hoursAndSpecials = listOf(
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.SUNDAY,
                        businessHours = "9AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Saturday & Sunday Football",
                                hours = "9AM - 2AM",
                                specials = listOf(
                                    Deal(
                                        description = "Bloody Mary's Well",
                                        price = "$7"
                                    ),
                                    Deal(
                                        description = "Bloody Mary's Titos",
                                        price = "$9"
                                    ),
                                    Deal(
                                        description = "22 oz double shot Skyy or Epsolon cocktails",
                                        price = "$16 (add Red Bull for $3.50)"
                                    ),
                                    Deal(
                                        description = "22 oz Mavericks Mimosas schooners",
                                        price = "$10"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.MONDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Happy Hour",
                                hours = "3PM - 7PM",
                                specials = listOf(
                                    Deal(
                                        description = "Half off shots, bottled & can beers",
                                        price = "50% off"
                                    ),
                                    Deal(
                                        description = "Wines ask server for selections",
                                        price = "$5.50 - 7.50"
                                    ),
                                    Deal(
                                        description = "Add $1 to any cocktail to make it 22oz double shot",
                                        price = "Add 1$"
                                    ),
                                    Deal(
                                        description = "Add $1 to any draft beer to make it 32oz double size",
                                        price = "Add 1$"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.TUESDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Happy Hour",
                                hours = "3PM - 7PM",
                                specials = listOf(
                                    Deal(
                                        description = "Half off shots, bottled & can beers",
                                        price = "50% off"
                                    ),
                                    Deal(
                                        description = "Wines ask server for selections",
                                        price = "$5.50 - 7.50"
                                    ),
                                    Deal(
                                        description = "Add $1 to any cocktail to make it 22oz double shot",
                                        price = "Add 1$"
                                    ),
                                    Deal(
                                        description = "Add $1 to any draft beer to make it 32oz double size",
                                        price = "Add 1$"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.WEDNESDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Happy Hour",
                                hours = "3PM - 7PM",
                                specials = listOf(
                                    Deal(
                                        description = "Half off shots, bottled & can beers",
                                        price = "50% off"
                                    ),
                                    Deal(
                                        description = "Wines ask server for selections",
                                        price = "$5.50 - 7.50"
                                    ),
                                    Deal(
                                        description = "Add $1 to any cocktail to make it 22oz double shot",
                                        price = "Add 1$"
                                    ),
                                    Deal(
                                        description = "Add $1 to any draft beer to make it 32oz double size",
                                        price = "Add 1$"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.THURSDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Happy Hour",
                                hours = "3PM - 7PM",
                                specials = listOf(
                                    Deal(
                                        description = "Half off shots, bottled & can beers",
                                        price = "50% off"
                                    ),
                                    Deal(
                                        description = "Wines ask server for selections",
                                        price = "$5.50 - 7.50"
                                    ),
                                    Deal(
                                        description = "Add $1 to any cocktail to make it 22oz double shot",
                                        price = "Add 1$"
                                    ),
                                    Deal(
                                        description = "Add $1 to any draft beer to make it 32oz double size",
                                        price = "Add 1$"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.FRIDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Happy Hour",
                                hours = "3PM - 7PM",
                                specials = listOf(
                                    Deal(
                                        description = "Half off shots, bottled & can beers",
                                        price = "50% off"
                                    ),
                                    Deal(
                                        description = "Wines ask server for selections",
                                        price = "$5.50 - 7.50"
                                    ),
                                    Deal(
                                        description = "Add $1 to any cocktail to make it 22oz double shot",
                                        price = "Add 1$"
                                    ),
                                    Deal(
                                        description = "Add $1 to any draft beer to make it 32oz double size",
                                        price = "Add 1$"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
                            )
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.SATURDAY,
                        businessHours = "9AM - 2AM",
                        specialEvents = listOf(
                            Event(
                                title = "Saturday & Sunday Football",
                                hours = "9AM - 2AM",
                                specials = listOf(
                                    Deal(
                                        description = "Bloody Mary's Well",
                                        price = "$7"
                                    ),
                                    Deal(
                                        description = "Bloody Mary's Titos",
                                        price = "$9"
                                    ),
                                    Deal(
                                        description = "22 oz double shot Skyy or Epsolon cocktails",
                                        price = "$16 (add Red Bull for $3.50)"
                                    ),
                                    Deal(
                                        description = "22 oz Mavericks Mimosas schooners",
                                        price = "$10"
                                    )
                                ),
                                image = URI("https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg")
                            )
                        )
                    )
                ),
                address = Address(
                    line1 = "53 Pier Ave",
                    line2 = "Hermosa Beach, CA 90254"
                ),
                phoneNumber = "(310) 379-6400",
                website = "https://tower12hb.com"
            )
        )
    }
}