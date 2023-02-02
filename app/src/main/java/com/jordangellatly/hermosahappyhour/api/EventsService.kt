package com.jordangellatly.hermosahappyhour.api

import com.jordangellatly.hermosahappyhour.model.Event
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface EventsService {

    @GET("/api/v1/events")
    suspend fun getAllEvents() : List<Event>

    @GET("/api/v1/events/{id}")
    suspend fun getEventById(@Path("id") eventId: UUID) : Event
}