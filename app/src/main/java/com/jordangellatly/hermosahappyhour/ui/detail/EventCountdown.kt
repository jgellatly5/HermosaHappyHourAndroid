package com.jordangellatly.hermosahappyhour.ui.detail

import android.os.CountDownTimer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.sundayBrunch
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EventCountdown(eventStart: String, eventEnd: String) {
    val annotatedTimeString = buildAnnotatedString {
        var millisInFuture = 0L
        var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
        val currentTime = Calendar.getInstance()
        val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val startDate = timestampFormat.parse(eventStart)
        val startTime = Calendar.getInstance().apply {
            if (startDate != null) {
                time = startDate
            }
        }
        val endDate = timestampFormat.parse(eventEnd)
        val endTime = Calendar.getInstance().apply {
            if (endDate != null) {
                time = endDate
            }
        }
        val stringStart = formatTimestamp(eventStart, "ha")
        val stringEnd = formatTimestamp(eventEnd, "ha")
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
        fontWeight = FontWeight.Bold,
        color = HermosaHappyHourTheme.colors.textSecondary,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
}

@Preview
@Composable
private fun EventCountdownPreview() {
    val event = sundayBrunch
    HermosaHappyHourTheme {
        EventCountdown(
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp
        )
    }
}