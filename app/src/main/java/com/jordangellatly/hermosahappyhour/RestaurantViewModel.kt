package com.jordangellatly.hermosahappyhour

import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {

    val sampleRestaurantData = listOf(
        Restaurant(
            "Tower12",
            "New American classics &a fancy snacks in a beachy, relaxed bar with romantic patio seating.",
            R.drawable.tower12
        ),
        Restaurant(
            "Sharkeez",
            "Lively bar & restaurant with simple Mexican fare plus lots of margaritas & a popular happy hour.",
            R.drawable.sharkeez
        ),
        Restaurant(
            "American Junkie",
            "Lively gastropub with American chow, craft brews & California wines plus multiple TVs for sports.",
            R.drawable.junkie
        ),
        Restaurant(
            "Henneseys",
            "Irish-style pub chain serving breakfast, bar bites, burgers & more in a casual, traditional space.",
            R.drawable.hennesseys
        )
    )
}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int
)