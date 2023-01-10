package com.jordangellatly.hermosahappyhour.model

import androidx.compose.runtime.mutableStateListOf
import java.net.URI

data class Event(
    val id: Long,
    val eventType: EventType,
    val title: String,
    val description: String,
    val startTimestamp: String,
    val endTimestamp: String,
    val weeklyHoursDescription: String,
    val restaurantId: Long,
    val specials: List<Deal>,
    val image: URI
)

data class Deal(
    val description: String,
    val price: String
)

enum class EventType { HappyHour, Brunch, Sports, Special, All }

val mondayHappyHour = Event(
    id = 1,
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-09T15:00",
    endTimestamp = "2023-01-09T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Wines (ask server for selections)",
            price = "$5.50 - 7.50"
        ),
        Deal(
            description = "To any cocktail to make it 22oz double shot",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it 32oz double size",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val tuesdayHappyHour = Event(
    id = 2,
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-10T15:00",
    endTimestamp = "2023-01-10T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Wines (ask server for selections)",
            price = "$5.50 - 7.50"
        ),
        Deal(
            description = "To any cocktail to make it 22oz double shot",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it 32oz double size",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val wednesdayHappyHour = Event(
    id = 3,
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-11T15:00",
    endTimestamp = "2023-01-11T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val thursdayHappyHour = Event(
    id = 4,
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-12T15:00",
    endTimestamp = "2023-01-12T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val fridayHappyHour = Event(
    id = 5,
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-13T15:00",
    endTimestamp = "2023-01-13T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val saturdayBrunch = Event(
    id = 6,
    eventType = EventType.Brunch,
    title = "Saturday Brunch",
    description = "Brunch is fun",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-07T09:00",
    endTimestamp = "2023-01-07T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
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

val saturdaySportEvent = Event(
    id = 7,
    eventType = EventType.Sports,
    title = "Saturday Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-07T15:00",
    endTimestamp = "2023-01-07T19:00",
    weeklyHoursDescription = "Today only \u2022 3PM - 7PM",
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

val sundayBrunch = Event(
    id = 8,
    eventType = EventType.Brunch,
    title = "Sunday Brunch",
    description = "Brunch is fun",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-08T09:00",
    endTimestamp = "2023-01-08T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
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

val sundaySportEvent = Event(
    id = 9,
    eventType = EventType.Sports,
    title = "Sunday Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-08T15:00",
    endTimestamp = "2023-01-08T19:00",
    weeklyHoursDescription = "Today only \u2022 3PM - 7PM",
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

val mondayNightFootball = Event(
    id = 10,
    eventType = EventType.Sports,
    title = "Monday Night Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-09T17:00",
    endTimestamp = "2023-01-09T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
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

val tacoTuesday = Event(
    id = 11,
    eventType = EventType.Special,
    title = "Taco Tuesday",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-10T17:00",
    endTimestamp = "2023-01-10T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
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

val wineWednesday = Event(
    id = 12,
    eventType = EventType.Special,
    title = "Wine Wednesday",
    description = "Wine Wednesday is fun",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-11T17:00",
    endTimestamp = "2023-01-11T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    specials = listOf(
        Deal(
            description = "Wine (ask server for selections)",
            price = "$7"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg")
)

val thursdayNightFootball = Event(
    id = 13,
    eventType = EventType.Sports,
    title = "Thursday Night Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-12T15:00",
    endTimestamp = "2023-01-12T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
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

val fridayNightTrivia = Event(
    id = 14,
    eventType = EventType.Special,
    title = "Friday Night Trivia",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-13T15:00",
    endTimestamp = "2023-01-13T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
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

val saturdayHappyHour = Event(
    id = 15,
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-07T15:00",
    endTimestamp = "2023-01-07T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val sundayHappyHour = Event(
    id = 16,
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-08T15:00",
    endTimestamp = "2023-01-08T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val sundaySilentDiscoSunset = Event(
    id = 17,
    eventType = EventType.Special,
    title = "Silent Disco Sunet",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-08T15:00",
    endTimestamp = "2023-01-08T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    specials = listOf(
        Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        ),
        Deal(
            description = "Add Red Bull",
            price = "$3"
        ),
        Deal(
            description = "To any well, call, or premium to make it a 22oz double shot double size cocktail",
            price = "Add 1$"
        ),
        Deal(
            description = "To any draft beer to make it a 22oz giant schooner",
            price = "Add 1$"
        )
    ),
    image = URI("https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg")
)

val mondayBrunch = Event(
    id = 18,
    eventType = EventType.Brunch,
    title = "Monday Brunch",
    description = "Brunch is fun",
    restaurantId = tower12RestaurantId,
    startTimestamp = "2023-01-09T09:00",
    endTimestamp = "2023-01-09T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
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

val sampleEventData = mutableStateListOf(
    mondayHappyHour,
    tuesdayHappyHour,
    wednesdayHappyHour,
    thursdayHappyHour,
    fridayHappyHour,
    mondayNightFootball,
    tacoTuesday,
    wineWednesday,
    thursdayNightFootball,
    fridayNightTrivia,
    saturdayBrunch,
    saturdaySportEvent,
    sundayBrunch,
    sundaySportEvent,
    saturdayHappyHour,
//    sundayHappyHour,
    sundaySilentDiscoSunset,
    mondayBrunch
)