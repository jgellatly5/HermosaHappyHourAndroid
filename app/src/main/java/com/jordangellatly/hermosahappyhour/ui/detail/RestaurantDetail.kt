package com.jordangellatly.hermosahappyhour.ui.detail

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.Event
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.detail.brunch.Brunch
import com.jordangellatly.hermosahappyhour.ui.detail.happyhour.HappyHour
import com.jordangellatly.hermosahappyhour.ui.detail.info.GeneralInfo
import com.jordangellatly.hermosahappyhour.ui.detail.special.SpecialEvent
import com.jordangellatly.hermosahappyhour.ui.detail.sports.Sports
import com.jordangellatly.hermosahappyhour.ui.home.formatTimestamp
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.getDayOfWeekFromTimestamp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RestaurantDetail(
    restaurantId: Long,
    upPress: () -> Unit
) {
    val date = getCurrentDateTime()
    val defaultFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formattedDateTimestamp = defaultFormat.format(date)
    val restaurant = remember(restaurantId) { RestaurantRepo.getRestaurant(restaurantId) }
    HermosaHappyHourSurface {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Header(
                restaurantName = restaurant.name,
                imageResource = restaurant.image,
                upPress = upPress
            )
            restaurant.eventsByDate.getValue(formattedDateTimestamp)[EventType.Brunch]?.let { event ->
                val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Brunch)
                Brunch(
                    weeklyHours = weeklyHours,
                    eventStart = event.startTimestamp,
                    eventEnd = event.endTimestamp,
                    specials = event.specials
                )
            }
            restaurant.eventsByDate.getValue(formattedDateTimestamp)[EventType.HappyHour]?.let { event ->
                val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.HappyHour)
                HappyHour(
                    weeklyHours = weeklyHours,
                    eventStart = event.startTimestamp,
                    eventEnd = event.endTimestamp,
                    specials = event.specials
                )
            }
            restaurant.eventsByDate.getValue(formattedDateTimestamp)[EventType.Sports]?.let { event ->
                val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Sports)
                Sports(
                    weeklyHours = weeklyHours,
                    eventStart = event.startTimestamp,
                    eventEnd = event.endTimestamp,
                    specials = event.specials
                )
            }
            restaurant.eventsByDate.getValue(formattedDateTimestamp)[EventType.Special]?.let { event ->
                val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Special)
                SpecialEvent(
                    weeklyHours = weeklyHours,
                    eventStart = event.startTimestamp,
                    eventEnd = event.endTimestamp,
                    specials = event.specials
                )
            }
            GeneralInfo(restaurant = restaurant)
        }
    }
}

@Composable
fun buildAnnotatedTimerString(event: Event) =
    buildAnnotatedString {
        var millisInFuture = 0L
        var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
        val currentTime = Calendar.getInstance()
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
        val stringStart = formatTimestamp(event.startTimestamp, "ha")
        val stringEnd = formatTimestamp(event.endTimestamp, "ha")
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

fun getWeeklyEventScheduleFromRestaurant(restaurant: Restaurant, eventType: EventType) =
    restaurant.eventsByDate
        .mapKeys { it.key.getDayOfWeekFromTimestamp() }
        .mapValues { it.value[eventType] }
        .mapValues {
            val brunchDayStart = it.value?.startTimestamp?.let { startTimestamp ->
                formatTimestamp(startTimestamp, "ha")
            } ?: ""
            val brunchDayEnd = it.value?.endTimestamp?.let { endTimestamp ->
                formatTimestamp(endTimestamp, "ha")
            } ?: ""
            if (brunchDayStart.isEmpty() || brunchDayEnd.isEmpty()) {
                "Not Available"
            } else {
                "$brunchDayStart - $brunchDayEnd"
            }
        }

@Composable
private fun Header(
    restaurantName: String,
    imageResource: Int,
    upPress: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(
                id = imageResource
            ),
            contentDescription = "Restaurant Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        TopAppBar(
            elevation = 0.dp,
            title = { Text(text = "") },
            backgroundColor = Color.Transparent
        )
        Up(upPress = upPress)
        Text(
            text = restaurantName,
            style = MaterialTheme.typography.h4,
            color = HermosaHappyHourTheme.colors.textInteractive,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = Neutral8.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = HermosaHappyHourTheme.colors.iconInteractive,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    HermosaHappyHourTheme {
        RestaurantDetail(
            restaurantId = 1,
            upPress = {}
        )
    }
}