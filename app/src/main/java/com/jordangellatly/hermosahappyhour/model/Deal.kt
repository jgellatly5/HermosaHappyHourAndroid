package com.jordangellatly.hermosahappyhour.model

data class Deal(
    val description: String,
    val price: String,
    val imageUrl: String = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
)

val tower12HappyHourSpecials = listOf(
    Deal(
        description = "Shots, bottled & can beers",
        price = "50% off",
        imageUrl = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
    ),
    Deal(
        description = "Wines (ask server for selections)",
        price = "$5.50 - 7.50",
        imageUrl = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
    ),
    Deal(
        description = "To any cocktail to make it a double sized double shot 22oz cocktail",
        price = "Add 1$",
        imageUrl = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
    ),
    Deal(
        description = "To any draft beer to make it a double size 32oz draft beer",
        price = "Add 1$",
        imageUrl = "https://cdn-icons-png.flaticon.com/512/187/187448.png"
    )
)