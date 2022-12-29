package com.jordangellatly.hermosahappyhour.ui.home

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.sampleSearchRestaurantData
import com.jordangellatly.hermosahappyhour.model.tower12RestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RestaurantCollection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
    ) {
        items(restaurants) { restaurant ->
            RestaurantItem(restaurant, onRestaurantClick)
        }
    }
}

@Preview
@Composable
private fun RestaurantCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurants = sampleSearchRestaurantData
        RestaurantCollection(
            restaurants = restaurants,
            onRestaurantClick = {}
        )
    }
}

@Composable
private fun RestaurantItem(
    restaurant: Restaurant,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HappyHourCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        val date = getCurrentDateTime()
        val getDayFromDate = date.toString("EEEE").uppercase()
        val happyHourToday =
            restaurant.weeklyEvents.getValue(getDayFromDate).getValue(EventType.HappyHour)
        val happyHours = happyHourToday.hours

        val currentTime = Calendar.getInstance()
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()

        val splitStartTimeFromHours = happyHours.split("-")
        val stringStart = splitStartTimeFromHours[0].trim()
        val stringEnd = splitStartTimeFromHours.get(index = 1).trim()

        val splitIntStartTime = stringStart.split("")
        var intStartFirstDigit = splitIntStartTime.get(index = 1).toInt()
        if (splitIntStartTime[2] == "P") {
            intStartFirstDigit += 12
        }

        val splitIntEndTime = stringEnd.split("")
        var intEndFirstDigit = splitIntEndTime[1].toInt()
        if (splitIntEndTime[2] == "P") {
            intEndFirstDigit += 12
        }

        startTime[Calendar.HOUR_OF_DAY] = intStartFirstDigit
        startTime[Calendar.MINUTE] = 0

        endTime[Calendar.HOUR_OF_DAY] = intEndFirstDigit
        endTime[Calendar.MINUTE] = 0

        Column(
            modifier = Modifier
                .clickable(onClick = { onRestaurantClick(restaurant.id) })
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                Box {
                    Image(
                        painter = painterResource(
                            id = restaurant.image
                        ),
                        contentDescription = "Restaurant Image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
            )
            var millisInFuture = 0L
            var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
            val annotatedTimeString = buildAnnotatedString {
                append("Happy Hour \u2022 ")
                when {
                    currentTime < startTime -> {
                        timeIndicatorColor = Color.Green
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Starts")
                        }
                        append(" at $stringStart")
                        millisInFuture = startTime.timeInMillis - currentTime.timeInMillis
                    }
                    currentTime > startTime && currentTime < endTime -> {
                        timeIndicatorColor = HermosaHappyHourTheme.colors.orange
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Ends")
                        }
                        append(" at $stringEnd")
                        millisInFuture = endTime.timeInMillis - currentTime.timeInMillis
                    }
                    currentTime > endTime -> {
                        timeIndicatorColor = Color.Red
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Ended")
                        }
                        append(" at $stringEnd")
                        millisInFuture = 0L
                    }
                    else -> {
                        append("")
                    }
                }
                append(" \u2022 ")

                val timeData = remember { mutableStateOf(millisInFuture) }
                val countDownTimer =
                    object : CountDownTimer(millisInFuture, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            timeData.value = millisUntilFinished
                        }

                        override fun onFinish() {}
                    }

                DisposableEffect(key1 = "key") {
                    countDownTimer.start()
                    onDispose {
                        countDownTimer.cancel()
                    }
                }

                val offset = SimpleDateFormat("HH:mm:ss", Locale.US)
                offset.timeZone = TimeZone.getTimeZone("GMT")
                val timeText = offset.format(timeData.value)
                withStyle(style = SpanStyle(timeIndicatorColor)) {
                    append(timeText)
                }
            }
            Text(
                text = annotatedTimeString,
                style = MaterialTheme.typography.body1,
                color = HermosaHappyHourTheme.colors.textHelp,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RestaurantItemPreview() {
    HermosaHappyHourTheme {
        val restaurant = tower12RestaurantData
        RestaurantItem(
            restaurant = restaurant,
            onRestaurantClick = {}
        )
    }
}