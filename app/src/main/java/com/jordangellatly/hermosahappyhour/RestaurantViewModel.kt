package com.jordangellatly.hermosahappyhour

import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {

    val sampleRestaurantData = listOf(
        Restaurant(
            "Tower12",
            "New American classics &a fancy snacks in a beachy, relaxed bar with romantic patio seating.",
            R.drawable.tower12,
            Location(
                latitude = 33.86222,
                longitude = -118.40085
            )
        ),
        Restaurant(
            "Baja Sharkeez",
            "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
            R.drawable.sharkeez,
            Location(
                latitude = 33.861988,
                longitude = -118.40071
            )
        ),
        Restaurant(
            "American Junkie",
            "Lively gastropub with American chow, craft brews & California wines plus multiple TVs for sports.",
            R.drawable.junkie,
            Location(
                latitude = 33.862,
                longitude = -118.40047
            )
        ),
        Restaurant(
            "Henneseys",
            "Irish-style pub chain serving breakfast, bar bites, burgers & more in a casual, traditional space.",
            R.drawable.hennesseys,
            Location(
                latitude = 33.86182,
                longitude = -118.40152
            )
        )
    )
}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int,
    val location: Location
)

data class Location(
    val latitude: Double,
    val longitude: Double
)