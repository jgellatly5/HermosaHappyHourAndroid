package com.jordangellatly.hermosahappyhour.api

import com.jordangellatly.hermosahappyhour.model.Event
import retrofit2.Response
import retrofit2.http.GET

interface EventsApi {

    @GET("/api/v1/events")
    suspend fun getAllEvents() : Response<List<Event>>
}