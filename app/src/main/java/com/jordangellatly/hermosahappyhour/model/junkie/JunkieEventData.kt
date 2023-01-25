package com.jordangellatly.hermosahappyhour.model.junkie

import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.americanJunkieRestaurantId
import java.util.*

val junkieFridayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    restaurantId = americanJunkieRestaurantId,
    startTimestamp = "2023-01-13T15:00",
    endTimestamp = "2023-01-13T19:00",
    weeklyHoursDescription = "Today only \u2022 4PM - 9PM",
    foodSpecials = junkieFridayHappyHourFoodSpecials,
    drinkSpecials = junkieFridayHappyHourDrinkSpecials,
    eventUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg"
)