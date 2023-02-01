package com.jordangellatly.hermosahappyhour.model

import android.util.LruCache

class EventsCache(
    private val eventsCache: LruCache<String, List<Event>> = LruCache(7)
) : EventsDataSource {

    override fun getEvents(formattedDate: String): List<Event> {
        return eventsCache.get(formattedDate)
    }

    override fun insertEvents(formattedDate: String, events: List<Event>) {
        eventsCache.put(formattedDate, events)
    }

    override fun clearAll() {
        eventsCache.evictAll()
    }
}

interface EventsDataSource {
    fun getEvents(formattedDate: String): List<Event>
    fun insertEvents(formattedDate: String, events: List<Event>)
    fun clearAll()
}