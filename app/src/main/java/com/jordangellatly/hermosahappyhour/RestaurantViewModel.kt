package com.jordangellatly.hermosahappyhour

import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {

    val restaurants = listOf(
        Restaurant("Tower12", "Best karaoke in town!", R.drawable.tower12),
        Restaurant("Sharkeez", "Best margaritas in town!", R.drawable.sharkeez),
        Restaurant("American Junkie", "Best taco tuesday in town!", R.drawable.junkie),
        Restaurant("Henneseys", "Best bloody mary in town!", R.drawable.hennesseys)
    )
}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int
)