package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun RestaurantHours(restaurant: Restaurant?) {
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
private fun RestaurantHoursPreview() {
    HermosaHappyHourTheme {
        RestaurantHours(
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
                            tower12SportEvent
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.MONDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            tower12HappyHour
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.TUESDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            tower12HappyHour
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.WEDNESDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            tower12HappyHour
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.THURSDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            tower12HappyHour
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.FRIDAY,
                        businessHours = "11AM - 2AM",
                        specialEvents = listOf(
                            tower12HappyHour
                        )
                    ),
                    HoursAndSpecials(
                        dayOfWeek = DayOfWeek.SATURDAY,
                        businessHours = "9AM - 2AM",
                        specialEvents = listOf(
                            tower12SportEvent
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