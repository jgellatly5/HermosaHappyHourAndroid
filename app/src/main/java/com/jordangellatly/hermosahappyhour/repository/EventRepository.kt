package com.jordangellatly.hermosahappyhour.repository

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.jordangellatly.hermosahappyhour.api.EventsApi
import com.jordangellatly.hermosahappyhour.api.RetrofitClient
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class EventRepository @Inject constructor() : EventRepo {

    private val retrofit = RetrofitClient.getInstance()
    private val eventsApi = retrofit.create(EventsApi::class.java)
    private val eventsDataSource: EventsDataSource = EventsCache()

    override fun getAllEventsByDate(date: Date): SnapshotStateList<Event> = sampleEventData.filter {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
        val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
        formattedCurrentDateString == formattedEventStartString
    }.toMutableStateList()

    override suspend fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event>? {
        try {
            val response = eventsApi.getAllEvents()
            return if (response.isSuccessful) {
                val events = response.body()

                events?.filter {
                    val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                    val currentDateTimestamp = defaultFormat.format(date)
                    val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd")
                    val formattedEventStartString = formatTimestamp(it.startTimestamp, "yyyy-MM-dd")
                    eventsDataSource.insertEvents(formattedCurrentDateString.toString(), events)
                    formattedCurrentDateString == formattedEventStartString && it.eventType == eventType
                }?.toMutableStateList()
            } else {
                null
            }
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("ERROR", it) }
            return null
        }
    }


//    override suspend fun getEventById(eventId: UUID): Event? {
//        return try {
//            val response = eventsApi.getEventById(eventId)
//            if (response.isSuccessful) {
//                response.body()
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            e.localizedMessage?.let { Log.e("ERROR", it) }
//            null
//        }
//    }

    override fun getEventById(eventId: UUID): Event {
        val date = getCurrentDateTime()
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val currentDateTimestamp = defaultFormat.format(date)
        val formattedCurrentDateString = formatTimestamp(currentDateTimestamp, "yyyy-MM-dd").toString()
        return eventsDataSource.getEvents(formattedCurrentDateString).find { it.id == eventId }!!
    }

    override fun getFilters(): SnapshotStateList<Filter> = filters
}

interface EventRepo {
    fun getAllEventsByDate(date: Date): SnapshotStateList<Event>
    suspend fun getEventsByDateAndType(date: Date, eventType: EventType): SnapshotStateList<Event>?
    fun getEventById(eventId: UUID): Event
    fun getFilters(): SnapshotStateList<Filter>
}