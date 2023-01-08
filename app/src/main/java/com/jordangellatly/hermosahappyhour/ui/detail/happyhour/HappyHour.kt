package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getDayOfWeekFromTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HappyHour(
    weeklyHappyHour: Map<String, Event?>,
    specials: List<Deal>,
    currentTime: Calendar,
    startTime: Calendar,
    endTime: Calendar,
    stringStart: String?,
    stringEnd: String?
) {
    Column(modifier = Modifier.padding(8.dp)) {
        val weeklyHours = weeklyHappyHour
            .mapValues {
                val happyHourDayStart = it.value?.startTimestamp?.let { startTimestamp ->
                    formatTimestamp(startTimestamp, "ha")
                } ?: ""
                val happyHourDayEnd = it.value?.endTimestamp?.let { endTimestamp ->
                    formatTimestamp(endTimestamp, "ha")
                } ?: ""
                if (happyHourDayStart.isEmpty() || happyHourDayEnd.isEmpty()) {
                    "Not Available"
                } else {
                    "$happyHourDayStart - $happyHourDayEnd"
                }
            }
        val annotatedTimeString = buildAnnotatedString {
            var millisInFuture = 0L
            var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
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
        HappyHourTimer(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString
        )

        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Happy Hour Specials",
                specials = specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HappyHourPreview() {
    val restaurant = tower12
    val event = saturdayHappyHour
    val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val startDate = timestampFormat.parse(event.startTimestamp)
    val startTime = Calendar.getInstance().apply {
        if (startDate != null) {
            time = startDate
        }
    }
    val endDate = timestampFormat.parse(event.endTimestamp)
    val endTime = Calendar.getInstance().apply {
        if (endDate != null) {
            time = endDate
        }
    }
    val currentTime = Calendar.getInstance()
    val stringStart = formatTimestamp(event.startTimestamp, "ha")
    val stringEnd = formatTimestamp(event.endTimestamp, "ha")

    val weeklyHappyHour = restaurant.eventsByDate
        .mapKeys { it.key.getDayOfWeekFromTimestamp() }
        .mapValues { it.value[EventType.HappyHour] }
    HermosaHappyHourTheme {
        HappyHour(
            weeklyHappyHour = weeklyHappyHour,
            specials = event.specials,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = stringStart,
            stringEnd = stringEnd
        )
    }
}