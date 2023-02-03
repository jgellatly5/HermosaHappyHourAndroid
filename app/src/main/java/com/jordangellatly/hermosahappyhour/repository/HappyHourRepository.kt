package com.jordangellatly.hermosahappyhour.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jordangellatly.hermosahappyhour.api.HappyHourApiService
import com.jordangellatly.hermosahappyhour.model.*
import java.util.*
import javax.inject.Inject

class HappyHourRepository @Inject constructor(private val happyHourApiService: HappyHourApiService) {

    private val eventsDataSource: EventsDataSource = EventsCache()

    suspend fun getAllEvents(): List<Event> = happyHourApiService.getAllEvents()

    suspend fun getEventById(eventId: UUID): Event? = happyHourApiService.getEventById(eventId)

    fun getFilters(): SnapshotStateList<Filter> = filters

    suspend fun getAllRestaurants(): List<Restaurant> = happyHourApiService.getAllRestaurants()

    suspend fun getRestaurantById(restaurantId: UUID): Restaurant? =
        happyHourApiService.getRestaurantById(restaurantId)
}