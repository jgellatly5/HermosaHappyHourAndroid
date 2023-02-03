package com.jordangellatly.hermosahappyhour.api

import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.Restaurant
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface HappyHourApiService {

    @GET("/api/v1/events")
    suspend fun getAllEvents() : List<Event>

    @GET("/api/v1/events/{id}")
    suspend fun getEventById(@Path("id") eventId: UUID) : Event?

    @GET("/api/v1/restaurants")
    suspend fun getAllRestaurants() : List<Restaurant>

    @GET("/api/v1/restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: UUID) : Restaurant?
}