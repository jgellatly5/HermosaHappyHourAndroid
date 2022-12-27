package com.jordangellatly.hermosahappyhour.ui.detail.info

import android.os.CountDownTimer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.SpecialsCollection
import com.jordangellatly.hermosahappyhour.model.tower12WeeklyHappyHour
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.detail.popup.HappyHourPopup
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HappyHourInfo(
    weeklyHappyHour: Map<String, Event>
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val getDayFromDate = date.toString("EEEE").uppercase()
        val happyHourToday = weeklyHappyHour[getDayFromDate]
        val happyHours = happyHourToday?.hours
        val specials = happyHourToday?.specials

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

        NextHappyHour(
            weeklyHappyHour = weeklyHappyHour,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = stringStart,
            stringEnd = stringEnd
        )

        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Happy Hour Specials",
                specials = specials ?: emptyList()
            ),
            onDealClick = {}
        )
    }
}

@Composable
private fun NextHappyHour(
    weeklyHappyHour: Map<String, Event>,
    currentTime: Calendar,
    startTime: Calendar,
    endTime: Calendar,
    stringStart: String?,
    stringEnd: String?
) {
    var popupControl by remember { mutableStateOf(false) }
    Column {
        var millisInFuture = 0L
        var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
        val annotatedTimeString = buildAnnotatedString {
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
                .clickable(
                    onClick = {
                        popupControl = true
                    }
                )
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        if (popupControl) {
            Popup(
                onDismissRequest = { popupControl = false }
            ) {
                HappyHourPopup(
                    weeklyHappyHour = weeklyHappyHour,
                    onClick = {
                        popupControl = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HappyHourInfoPreview() {
    HermosaHappyHourTheme {
        HappyHourInfo(
            weeklyHappyHour = tower12WeeklyHappyHour
        )
    }
}