package com.jordangellatly.hermosahappyhour.model

import android.util.Log
import com.google.gson.Gson
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.junkie.junkieFridayHappyHour
import com.jordangellatly.hermosahappyhour.model.tower12.*
import java.util.*

data class Restaurant(
    val id: UUID,
    val name: String,
    val description: String,
    val companyLogoUrl: String,
    val image: Int,
    val location: Location,
    val weeklyHours: Map<String, String>,
    val eventsByDate: Map<String, Map<EventType, UUID>>,
    val address: Address,
    val phoneNumber: String,
    val website: String
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class Address(
    val line1: String,
    val line2: String
)

val generalWeeklyHours = mapOf(
    "SUNDAY" to "9AM - 2AM",
    "MONDAY" to "11AM - 2AM",
    "TUESDAY" to "11AM - 2AM",
    "WEDNESDAY" to "11AM - 2AM",
    "THURSDAY" to "11AM - 2AM",
    "FRIDAY" to "11AM - 2AM",
    "SATURDAY" to "9AM - 2AM"
)

val tower12EventsByDate = mapOf(
    "2023-02-05" to mapOf(
        EventType.Brunch to sundayBrunch.id,
        EventType.Sports to sundaySportEvent.id,
        EventType.Special to sundaySilentDiscoSunset.id
    ),
    "2023-02-06" to mapOf(
        EventType.HappyHour to tower12MondayHappyHour.id,
        EventType.Sports to mondayNightFootball.id
    ),
    "2023-02-07" to mapOf(
        EventType.HappyHour to tower12TuesdayHappyHour.id,
        EventType.Special to tacoTuesday.id
    ),
    "2023-02-08" to mapOf(
        EventType.HappyHour to tower12WednesdayHappyHour.id,
        EventType.Special to wineWednesday.id
    ),
    "2023-02-09" to mapOf(
        EventType.HappyHour to tower12ThursdayHappyHour.id,
        EventType.Sports to thursdayNightFootball.id
    ),
    "2023-02-03" to mapOf(
        EventType.HappyHour to tower12FridayHappyHour.id,
        EventType.Special to fridayNightTrivia.id
    ),
    "2023-02-04" to mapOf(
        EventType.Brunch to saturdayBrunch.id,
        EventType.Sports to saturdaySportEvent.id
    )
)

val junkieEventsByDate = mapOf(
    "2023-01-15" to mapOf(
        EventType.Brunch to sundayBrunch.id,
        EventType.Sports to sundaySportEvent.id,
        EventType.Special to sundaySilentDiscoSunset.id
    ),
    "2023-01-16" to mapOf(
        EventType.Brunch to mondayBrunch.id,
        EventType.HappyHour to tower12MondayHappyHour.id,
        EventType.Sports to mondayNightFootball.id
    ),
    "2023-01-17" to mapOf(
        EventType.HappyHour to tower12TuesdayHappyHour.id
    ),
    "2023-01-18" to mapOf(
        EventType.HappyHour to tower12WednesdayHappyHour.id
    ),
    "2023-01-19" to mapOf(
        EventType.HappyHour to tower12ThursdayHappyHour.id
    ),
    "2023-01-13" to mapOf(
        EventType.HappyHour to junkieFridayHappyHour.id
    ),
    "2023-01-14" to mapOf(
        EventType.Brunch to saturdayBrunch.id,
        EventType.Sports to saturdaySportEvent.id
    )
)

val tower12 = Restaurant(
    id = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    name = "Tower 12",
    description = "New American classics & fancy snacks in a beachy, relaxed bar with romantic patio seating.",
    companyLogoUrl = "https://scontent-lax3-2.xx.fbcdn.net/v/t39.30808-6/292336365_435159251951538_4078078271979326231_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=C_tAC12x4o0AX9uN-Yg&_nc_ht=scontent-lax3-2.xx&oh=00_AfAAxhYzzBx-bK8gDhRHmOeMrzUxDDsDMZeCaTyFHRr3Ug&oe=638F052F",
    image = R.drawable.tower12,
    location = Location(
        latitude = 33.86222,
        longitude = -118.40085
    ),
    weeklyHours = generalWeeklyHours,
    eventsByDate = tower12EventsByDate,
    address = Address(
        line1 = "53 Pier Ave",
        line2 = "Hermosa Beach, CA 90254"
    ),
    phoneNumber = "(310) 379-6400",
    website = "https://tower12hb.com"
)

val bajaSharkeez = Restaurant(
    id = UUID.fromString("0a5092b9-9e8a-4ac1-bf52-5d268aa3b70d"),
    name = "Baja Sharkeez",
    description = "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
    companyLogoUrl = "https://sharkeez.net/wp-content/uploads/2020/11/sharkeez-light.png",
    image = R.drawable.sharkeez,
    location = Location(
        latitude = 33.861988,
        longitude = -118.40071
    ),
    weeklyHours = generalWeeklyHours,
    eventsByDate = tower12EventsByDate,
    address = Address(
        line1 = "52 Pier Ave",
        line2 = "Hermosa Beach, CA 90254"
    ),
    phoneNumber = "(310) 318-0004",
    website = "https://sharkeez.net/"
)

val americanJunkie = Restaurant(
    id = UUID.fromString("083970d5-e602-43b2-b50c-d11756cfafcb"),
    name = "American Junkie",
    description = "Lively gastropub with American chow, craft brews & California wines plus multiple TVs for sports.",
    companyLogoUrl = "https://images.squarespace-cdn.com/content/v1/5a340b32f09ca4f1abede53f/1513369024515-81MNDBH8WH7EFSWJ6BKE/AJ+Gargoyle+White+Logo.png?format=1500w",
    image = R.drawable.junkie,
    location = Location(
        latitude = 33.862,
        longitude = -118.40047
    ),
    weeklyHours = generalWeeklyHours,
    eventsByDate = tower12EventsByDate,
    address = Address(
        line1 = "68 Pier Ave",
        line2 = "Hermosa Beach, CA 90254"
    ),
    phoneNumber = "(310) 376-4412",
    website = "https://www.americanjunkiehb.com/"
)

val hennesseys = Restaurant(
    id = UUID.fromString("7239cc56-0628-45f7-98dc-ab897ef62d6e"),
    name = "Henneseys",
    description = "Irish-style pub chain serving breakfast, bar bites, burgers & more in a casual, traditional space.",
    companyLogoUrl = "https://images.squarespace-cdn.com/content/v1/5c5db1e83560c36d822b0633/1554189473268-4OGP4B9IXAUV2K2EBKU6/hennesseys_tavern_logo.png?format=1500w",
    image = R.drawable.hennesseys,
    location = Location(
        latitude = 33.86182,
        longitude = -118.40152
    ),
    weeklyHours = generalWeeklyHours,
    eventsByDate = tower12EventsByDate,
    address = Address(
        line1 = "8 Pier Ave",
        line2 = "Hermosa Beach, CA 90254"
    ),
    phoneNumber = "(310) 372-5759",
    website = "https://www.hennesseystavern.com/locations-hermosa-beach"
)

val sampleSearchRestaurantData = listOf(
    tower12,
    bajaSharkeez,
    americanJunkie,
    hennesseys
)

fun printRestaurantsJson() {
    val restaurants = sampleSearchRestaurantData
    for (restaurant in restaurants) {
        val gson = Gson()
        val json = gson.toJson(restaurant)
        Log.d("Restaurants JSON", json)
    }
}