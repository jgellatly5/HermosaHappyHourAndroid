package com.jordangellatly.hermosahappyhour.model.junkie

import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import java.util.*

val junkieFridayHappyHour = Event(
    id = UUID.randomUUID(),
    eventType = EventType.HappyHour,
    title = "Happy Hour",
    restaurantId = UUID.fromString("083970d5-e602-43b2-b50c-d11756cfafcb"),
    restaurantName = "American Junkie",
    startTimestamp = "2023-01-13T15:00",
    endTimestamp = "2023-01-13T19:00",
    weeklyHoursDescription = "Today only \u2022 4PM - 9PM",
    foodSpecials = junkieFridayHappyHourFoodSpecials,
    drinkSpecials = junkieFridayHappyHourDrinkSpecials,
    eventInfoUrl = "https://tower12hb.com/wp-content/uploads/2022/01/TR-Jungle-Hour-4x6-1.jpg",
    eventImageUrl = "https://cdn.vox-cdn.com/thumbor/DTEjhFGI77hFOd964vCTSiqsQ90=/0x0:2000x1333/1820x728/filters:focal(840x507:1160x827):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/53515463/tower_12_hermosa.0.jpg"
)