package com.jordangellatly.hermosahappyhour.model

import com.jordangellatly.hermosahappyhour.R

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    val companyLogoUrl: String,
    val image: Int,
    val location: Location,
    val weeklyHours: Map<String, String>,
    val weeklyEvents: Map<String, Map<EventType, Event>>,
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

val tower12WeeklyHours = mapOf(
    "SUNDAY" to "9AM - 2AM",
    "MONDAY" to "11AM - 2AM",
    "TUESDAY" to "11AM - 2AM",
    "WEDNESDAY" to "11AM - 2AM",
    "THURSDAY" to "11AM - 2AM",
    "FRIDAY" to "11AM - 2AM",
    "SATURDAY" to "9AM - 2AM"
)

val tower12WeeklyHappyHour = mapOf(
    "MONDAY" to tower12HappyHour,
    "TUESDAY" to tower12HappyHour,
    "WEDNESDAY" to tower12HappyHour,
    "THURSDAY" to tower12HappyHour,
    "FRIDAY" to tower12HappyHour
)

val tower12WeeklyEvents = mapOf(
    "SUNDAY" to mapOf(
        EventType.Sports to tower12SportEvent
    ),
    "MONDAY" to mapOf(
        EventType.HappyHour to tower12HappyHour,
        EventType.Sports to mondayNightFootball
    ),
    "TUESDAY" to mapOf(
        EventType.HappyHour to tower12HappyHour,
        EventType.Default to tacoTuesday
    ),
    "WEDNESDAY" to mapOf(
        EventType.HappyHour to tower12HappyHour,
        EventType.Default to wineWednesday
    ),
    "THURSDAY" to mapOf(
        EventType.HappyHour to tower12HappyHour,
        EventType.Sports to thursdayNightFootball
    ),
    "FRIDAY" to mapOf(
        EventType.HappyHour to tower12HappyHour,
        EventType.Default to fridayNightTrivia
    ),
    "SATURDAY" to mapOf(
        EventType.Sports to tower12SportEvent
    )
)

val sampleSearchRestaurantData = listOf(
    Restaurant(
        id = 1,
        name = "Tower 12",
        description = "New American classics & fancy snacks in a beachy, relaxed bar with romantic patio seating.",
        companyLogoUrl = "https://scontent-lax3-2.xx.fbcdn.net/v/t39.30808-6/292336365_435159251951538_4078078271979326231_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=C_tAC12x4o0AX9uN-Yg&_nc_ht=scontent-lax3-2.xx&oh=00_AfAAxhYzzBx-bK8gDhRHmOeMrzUxDDsDMZeCaTyFHRr3Ug&oe=638F052F",
        image = R.drawable.tower12,
        location = Location(
            latitude = 33.86222,
            longitude = -118.40085
        ),
        weeklyHours = tower12WeeklyHours,
        weeklyEvents = tower12WeeklyEvents,
        address = Address(
            line1 = "53 Pier Ave",
            line2 = "Hermosa Beach, CA 90254"
        ),
        phoneNumber = "(310) 379-6400",
        website = "https://tower12hb.com"
    ),
    Restaurant(
        id = 2,
        name = "Baja Sharkeez",
        description = "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
        companyLogoUrl = "https://sharkeez.net/wp-content/uploads/2020/11/sharkeez-light.png",
        image = R.drawable.sharkeez,
        location = Location(
            latitude = 33.861988,
            longitude = -118.40071
        ),
        weeklyHours = tower12WeeklyHours,
        weeklyEvents = tower12WeeklyEvents,
        address = Address(
            line1 = "52 Pier Ave",
            line2 = "Hermosa Beach, CA 90254"
        ),
        phoneNumber = "(310) 318-0004",
        website = "https://sharkeez.net/"
    ),
    Restaurant(
        id = 3,
        name = "American Junkie",
        description = "Lively gastropub with American chow, craft brews & California wines plus multiple TVs for sports.",
        companyLogoUrl = "https://images.squarespace-cdn.com/content/v1/5a340b32f09ca4f1abede53f/1513369024515-81MNDBH8WH7EFSWJ6BKE/AJ+Gargoyle+White+Logo.png?format=1500w",
        image = R.drawable.junkie,
        location = Location(
            latitude = 33.862,
            longitude = -118.40047
        ),
        weeklyHours = tower12WeeklyHours,
        weeklyEvents = tower12WeeklyEvents,
        address = Address(
            line1 = "68 Pier Ave",
            line2 = "Hermosa Beach, CA 90254"
        ),
        phoneNumber = "(310) 376-4412",
        website = "https://www.americanjunkiehb.com/"
    ),
    Restaurant(
        id = 4,
        name = "Henneseys",
        description = "Irish-style pub chain serving breakfast, bar bites, burgers & more in a casual, traditional space.",
        companyLogoUrl = "https://images.squarespace-cdn.com/content/v1/5c5db1e83560c36d822b0633/1554189473268-4OGP4B9IXAUV2K2EBKU6/hennesseys_tavern_logo.png?format=1500w",
        image = R.drawable.hennesseys,
        location = Location(
            latitude = 33.86182,
            longitude = -118.40152
        ),
        weeklyHours = tower12WeeklyHours,
        weeklyEvents = tower12WeeklyEvents,
        address = Address(
            line1 = "8 Pier Ave",
            line2 = "Hermosa Beach, CA 90254"
        ),
        phoneNumber = "(310) 372-5759",
        website = "https://www.hennesseystavern.com/locations-hermosa-beach"
    )
)

val tower12RestaurantData = sampleSearchRestaurantData[0]