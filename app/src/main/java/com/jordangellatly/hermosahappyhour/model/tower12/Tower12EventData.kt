package com.jordangellatly.hermosahappyhour.model.tower12

import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import java.util.*

val tower12MondayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-23T15:00",
    endTimestamp = "2023-01-23T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = tower12JungleHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val tower12TuesdayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-24T15:00",
    endTimestamp = "2023-01-24T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = tower12JungleHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val tower12WednesdayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-25T15:00",
    endTimestamp = "2023-01-25T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = tower12JungleHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val tower12ThursdayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-26T15:00",
    endTimestamp = "2023-01-26T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = tower12JungleHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val tower12FridayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Jungle Hour",
    description = "Bringing you wild drink specials",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-27T15:00",
    endTimestamp = "2023-01-27T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = tower12JungleHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val saturdayBrunch = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Brunch,
    title = "Saturday Brunch",
    description = "Brunch is fun",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-28T09:00",
    endTimestamp = "2023-01-28T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val saturdaySportEvent = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Sports,
    title = "Saturday Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-28T15:00",
    endTimestamp = "2023-01-28T19:00",
    weeklyHoursDescription = "Today only \u2022 3PM - 7PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val sundayBrunch = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Brunch,
    title = "Sunday Brunch",
    description = "Brunch is fun",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-29T09:00",
    endTimestamp = "2023-01-29T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val sundaySportEvent = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Sports,
    title = "Sunday Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-29T15:00",
    endTimestamp = "2023-01-29T19:00",
    weeklyHoursDescription = "Today only \u2022 3PM - 7PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val mondayNightFootball = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Sports,
    title = "Monday Night Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-23T17:00",
    endTimestamp = "2023-01-23T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val tacoTuesday = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Special,
    title = "Taco Tuesday",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-24T17:00",
    endTimestamp = "2023-01-24T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val wineWednesday = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Special,
    title = "Wine Wednesday",
    description = "Wine Wednesday is fun",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-25T17:00",
    endTimestamp = "2023-01-25T20:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
        Deal(
            description = "Wine (ask server for selections)",
            price = "$7"
        )
    ),
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val thursdayNightFootball = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Sports,
    title = "Thursday Night Football",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-26T15:00",
    endTimestamp = "2023-01-26T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val fridayNightTrivia = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Special,
    title = "Friday Night Trivia",
    description = "Premiere sports viewing, we get all the games",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-27T15:00",
    endTimestamp = "2023-01-27T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)

val saturdayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-28T15:00",
    endTimestamp = "2023-01-28T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val sundayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-29T15:00",
    endTimestamp = "2023-01-29T19:00",
    weeklyHoursDescription = "M - F \u2022 3PM - 7PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val sundaySilentDiscoSunset = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Special,
    title = "Silent Disco Sunet",
    description = "Serving sunsets, ice cold drinks, & good vibes daily",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-29T15:00",
    endTimestamp = "2023-01-29T19:00",
    weeklyHoursDescription = "Today only \u2022 5PM - 8PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)

val mondayBrunch = Event(
    id = UUID.randomUUID(),
    eventType = EventType.Brunch,
    title = "Monday Brunch",
    description = "Brunch is fun",
    restaurantId = UUID.fromString("1833bcfa-a7bf-456d-b0fe-5030cbe962e4"),
    startTimestamp = "2023-01-23T09:00",
    endTimestamp = "2023-01-23T14:00",
    weeklyHoursDescription = "Sat & Sun \u2022 9AM - 2PM",
    drinkSpecials = listOf(
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
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/08/TR-Sat-Sun-4x6-1-696x1024.jpg"
)