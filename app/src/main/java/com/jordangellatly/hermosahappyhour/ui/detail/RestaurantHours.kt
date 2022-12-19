package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.DayOfWeek
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.tower12RestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun RestaurantHours(restaurant: Restaurant?) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .border(
                width = 2.dp,
                color = Color.Black
            )
            .padding(8.dp)
    ) {
        Text(
            text = "Hours",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
        DayOfWeek.values().forEach { dayOfWeek ->
            val dailyInfo = restaurant?.hoursAndSpecials?.find { it.dayOfWeek == dayOfWeek }
            val businessHours = dailyInfo?.businessHours
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = dayOfWeek.toString().lowercase().replaceFirstChar {
                        it.uppercase()
                    },
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = businessHours.toString(),
                    style = MaterialTheme.typography.h6,
                )
            }
            HappyHourDivider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantHoursPreview() {
    HermosaHappyHourTheme {
        RestaurantHours(
            restaurant = tower12RestaurantData
        )
    }
}