package com.jordangellatly.hermosahappyhour

import androidx.lifecycle.ViewModel
import java.net.URI

class RestaurantViewModel : ViewModel() {

    val sampleRestaurantData = listOf(
        Restaurant(
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
        ),
        Restaurant(
            name = "Baja Sharkeez",
            description = "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
            image = R.drawable.sharkeez,
            location = Location(
                latitude = 33.861988,
                longitude = -118.40071
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
                Pair(DayOfWeek.THURSDAY, ""),
                Pair(DayOfWeek.FRIDAY, ""),
                Pair(DayOfWeek.SATURDAY, "")
            ),
            specialEvent = Event(
                title = "Thursday Night Karaoke",
                description = "Be brave and sing your lungs out for everyone in the back bar.",
                image = URI("https://google.com")
            ),
            address = Address(
                line1 = "52 Pier Ave",
                line2 = "Hermosa Beach, CA 90254"
            ),
            phoneNumber = "(310) 318-0004"
        ),
        Restaurant(
            name = "American Junkie",
            description = "Lively gastropub with American chow, craft brews & California wines plus multiple TVs for sports.",
            image = R.drawable.junkie,
            location = Location(
                latitude = 33.862,
                longitude = -118.40047
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
                Pair(DayOfWeek.THURSDAY, ""),
                Pair(DayOfWeek.FRIDAY, ""),
                Pair(DayOfWeek.SATURDAY, "")
            ),
            specialEvent = Event(
                title = "Thursday Night Karaoke",
                description = "Be brave and sing your lungs out for everyone in the back bar.",
                image = URI("https://google.com")
            ),
            address = Address(
                line1 = "68 Pier Ave",
                line2 = "Hermosa Beach, CA 90254"
            ),
            phoneNumber = "(310) 376-4412"
        ),
        Restaurant(
            name = "Henneseys",
            description = "Irish-style pub chain serving breakfast, bar bites, burgers & more in a casual, traditional space.",
            image = R.drawable.hennesseys,
            location = Location(
                latitude = 33.86182,
                longitude = -118.40152
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
                Pair(DayOfWeek.THURSDAY, ""),
                Pair(DayOfWeek.FRIDAY, ""),
                Pair(DayOfWeek.SATURDAY, "")
            ),
            specialEvent = Event(
                title = "Thursday Night Karaoke",
                description = "Be brave and sing your lungs out for everyone in the back bar.",
                image = URI("https://google.com")
            ),
            address = Address(
                line1 = "8 Pier Ave",
                line2 = "Hermosa Beach, CA 90254"
            ),
            phoneNumber = "(310) 372-5759"
        )
    )
}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int,
    val location: Location,
    val businessHours: Map<DayOfWeek, String>,
    val happyHours: Map<DayOfWeek, String>,
    val happyHourInfo: HappyHourInfo,
    val dailyEvents: Map<DayOfWeek, String>,
    val specialEvent: Event,
    val address: Address,
    val phoneNumber: String
)

data class HappyHourInfo(
    val hours: String,
    val specials: List<String>
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class Address(
    val line1: String,
    val line2: String
)

data class Event(
    val title: String,
    val description: String,
    val image: URI
)

enum class DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY
}