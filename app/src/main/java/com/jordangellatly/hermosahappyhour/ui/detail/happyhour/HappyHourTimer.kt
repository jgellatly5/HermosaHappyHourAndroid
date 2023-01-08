package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import android.os.CountDownTimer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.eventsByDateForTesting
import com.jordangellatly.hermosahappyhour.model.saturdayHappyHour
import com.jordangellatly.hermosahappyhour.ui.detail.popup.HoursPopup
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getDayOfWeekFromTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HappyHourTimer(
    weeklyHours: Map<String, String>,
    annotatedTimeString: AnnotatedString
) {
    var popupControl by remember { mutableStateOf(false) }
    Column {
        Text(
            text = "Happy Hour",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = annotatedTimeString,
            fontWeight = FontWeight.Bold,
            color = HermosaHappyHourTheme.colors.textSecondary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
        Text(
            text = "See all happy hours \u279E",
            fontWeight = FontWeight.Bold,
            color = HermosaHappyHourTheme.colors.brand,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .clickable(onClick = { popupControl = true })
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        if (popupControl) {
            HoursPopup(
                title = "Happy Hour",
                weeklyHours = weeklyHours,
                onClick = { popupControl = false },
                onDismissRequest = { popupControl = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HappyHourTimerPreview() {
    val happyHourEvent = saturdayHappyHour
    val weeklyHappyHour = eventsByDateForTesting
        .mapKeys { it.key.getDayOfWeekFromTimestamp() }
        .mapValues { it.value[EventType.HappyHour] }
    val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val startDate = timestampFormat.parse(happyHourEvent.startTimestamp)
    val startTime = Calendar.getInstance().apply {
        if (startDate != null) {
            time = startDate
        }
    }
    val endDate = timestampFormat.parse(happyHourEvent.endTimestamp)
    val endTime = Calendar.getInstance().apply {
        if (endDate != null) {
            time = endDate
        }
    }
    val currentTime = Calendar.getInstance()
    val stringStart = formatTimestamp(happyHourEvent.startTimestamp, "ha")
    val stringEnd = formatTimestamp(happyHourEvent.endTimestamp, "ha")
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
    HermosaHappyHourTheme {
        HappyHourTimer(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString
        )
    }
}