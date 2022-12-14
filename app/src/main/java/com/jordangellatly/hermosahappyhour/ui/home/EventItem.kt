package com.jordangellatly.hermosahappyhour.ui.home

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.model.tower12MondayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourCard
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EventItem(
    event: Event,
    onEventClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HappyHourCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())

        val startDate = defaultFormat.parse(event.startTimestamp)
        val startTime = Calendar.getInstance().apply {
            if (startDate != null) {
                time = startDate
            }
        }

        val endDate = defaultFormat.parse(event.endTimestamp)
        val endTime = Calendar.getInstance().apply {
            if (endDate != null) {
                time = endDate
            }
        }

        val currentTime = Calendar.getInstance()

        val formattedStart = formatTimestamp(event.startTimestamp, "ha")
        val formattedEnd = formatTimestamp(event.endTimestamp, "ha")

        Column(
            modifier = Modifier
                .clickable(onClick = { onEventClick(event.restaurantId) })
                .fillMaxSize()
        ) {
            val restaurant = remember { RestaurantRepo.getRestaurant(event.restaurantId) }
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
                append("${event.title} \u2022 ")
                when {
                    currentTime < startTime -> {
                        timeIndicatorColor = Color.Green
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Starts")
                        }
                        append(" at $formattedStart")
                        millisInFuture = startTime.timeInMillis - currentTime.timeInMillis
                    }
                    currentTime > startTime && currentTime < endTime -> {
                        timeIndicatorColor = HermosaHappyHourTheme.colors.orange
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Ends")
                        }
                        append(" at $formattedEnd")
                        millisInFuture = endTime.timeInMillis - currentTime.timeInMillis
                    }
                    currentTime > endTime -> {
                        timeIndicatorColor = Color.Red
                        withStyle(style = SpanStyle(timeIndicatorColor)) {
                            append("Ended")
                        }
                        append(" at $formattedEnd")
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

@Preview(heightDp = 240)
@Composable
private fun EventItemPreview() {
    HermosaHappyHourTheme {
        val event = tower12MondayHappyHour
        EventItem(
            event = event,
            onEventClick = {}
        )
    }
}