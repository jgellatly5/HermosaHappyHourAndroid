package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.sampleSearchRestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.components.RestaurantImage
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.util.*

@Composable
fun HappyHourRestaurantCollection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(restaurants) { restaurant ->
            HappyHourItem(restaurant, onRestaurantClick)
        }
    }
}

@Preview
@Composable
fun HappyHourCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        HappyHourRestaurantCollection(
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun HappyHourItem(
    restaurant: Restaurant,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp,
            bottom = 8.dp
        )
    ) {
        val date = getCurrentDateTime()
        val getDayFromDate = date.toString("EEEE").uppercase()
        val hoursAndEventsToday =
            restaurant.weeklyHoursAndSpecials.find { it.dayOfWeek.toString() == getDayFromDate }
        val happyHourEvent = hoursAndEventsToday?.specialEvents?.first()
        val happyHours = happyHourEvent?.hours

        val currentTime = Calendar.getInstance()
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()

        val splitStartTimeFromHours = happyHours?.split("-")
        val stringStart = splitStartTimeFromHours?.get(0)?.trim()
        val stringEnd = splitStartTimeFromHours?.get(1)?.trim()

        val splitIntStartTime = stringStart?.split("")
        var intStartFirstDigit = splitIntStartTime?.get(1)?.toInt()
        if (splitIntStartTime?.get(2).equals("P")) {
            intStartFirstDigit = intStartFirstDigit?.plus(12)
        }

        val splitIntEndTime = stringEnd?.split("")
        var intEndFirstDigit = splitIntEndTime?.get(1)?.toInt()
        if (splitIntEndTime?.get(2).equals("P")) {
            intEndFirstDigit = intEndFirstDigit?.plus(12)
        }

        if (intStartFirstDigit != null) {
            startTime[Calendar.HOUR_OF_DAY] = intStartFirstDigit
        }
        startTime[Calendar.MINUTE] = 0

        if (intEndFirstDigit != null) {
            endTime[Calendar.HOUR_OF_DAY] = intEndFirstDigit
        }
        endTime[Calendar.MINUTE] = 0

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = { onRestaurantClick(restaurant.id) })
                .padding(8.dp)
        ) {
            RestaurantImage(
                imageUrl = restaurant.companyLogoUrl,
                elevation = 4.dp,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            val annotatedTimeString = buildAnnotatedString {
                when {
                    currentTime < startTime -> {
                        withStyle(style = SpanStyle(Color.Green)) {
                            append("Starts")
                        }
                        append(" at $stringStart")
                    }
                    currentTime > startTime && currentTime < endTime -> {
                        withStyle(style = SpanStyle(HermosaHappyHourTheme.colors.orange)) {
                            append("Ends")
                        }
                        append(" at $stringEnd")
                    }
                    currentTime > endTime -> {
                        withStyle(style = SpanStyle(Color.Red)) {
                            append("Ended")
                        }
                        append(" at $stringEnd")
                    }
                    else -> {
                        append("")
                    }
                }

            }
            Text(
                text = annotatedTimeString,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun HappyHourItemPreview() {
    HermosaHappyHourTheme {
        val restaurant = sampleSearchRestaurantData.first()
        HappyHourItem(
            restaurant = restaurant,
            onRestaurantClick = {}
        )
    }
}