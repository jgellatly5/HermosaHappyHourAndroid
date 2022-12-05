package com.jordangellatly.hermosahappyhour.model

import com.jordangellatly.hermosahappyhour.R
import java.net.URI

data class Restaurant(
    val id: Long,
    val name: String,
    val description: String,
    val companyLogoUrl: String,
    val image: Int,
    val location: Location,
    val hoursAndSpecials: List<HoursAndSpecials>,
    val address: Address,
    val phoneNumber: String,
    val website: String
)

data class HoursAndSpecials(
    val dayOfWeek: DayOfWeek,
    val businessHours: String,
    val specialEvents: List<Event>
)

data class Deal(
    val description: String,
    val price: String
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
    val hours: String,
    val specials: List<Deal>,
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

val sampleSearchRestaurantData = listOf(
    Restaurant(
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
                        title = "Weekday Happy Hour",
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
            line1 = "8 Pier Ave",
            line2 = "Hermosa Beach, CA 90254"
        ),
        phoneNumber = "(310) 372-5759",
        website = "https://www.hennesseystavern.com/locations-hermosa-beach"
    )
)

val sampleHomeRestaurantData = listOf(
    sampleSearchRestaurantData[0]
)