package com.jordangellatly.hermosahappyhour.ui.detail

import android.os.CountDownTimer
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
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
    val restaurant = remember(restaurantId) { RestaurantRepo.getRestaurant(restaurantId) }
    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        },
        backgroundColor = HermosaHappyHourTheme.colors.uiBackground
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            Header(restaurant = restaurant, upPress = upPress)
            Specials(restaurant = restaurant)
            GeneralInfo(restaurant = restaurant)
        }
    }
}

@Composable
private fun Header(
    restaurant: Restaurant?,
    upPress: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(
                id = restaurant?.image ?: R.drawable.tower12
            ),
            contentDescription = null,
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
            text = restaurant?.name.toString(),
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

@Composable
private fun Specials(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val getDayFromDate = date.toString("EEEE").uppercase()
        val hoursAndEventsToday =
            restaurant?.hoursAndSpecials?.find { it.dayOfWeek.toString() == getDayFromDate }
        val happyHourEvent = hoursAndEventsToday?.specialEvents?.find { it.title == "Happy Hour" }
        val happyHours = happyHourEvent?.hours
        val specials = happyHourEvent?.specials

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
            restaurant = restaurant,
            currentTime = currentTime,
            startTime = startTime,
            endTime = endTime,
            stringStart = stringStart,
            stringEnd = stringEnd
        )

        Text(
            text = "Happy Hour Specials",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Column {
            specials?.forEach { deal ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = deal.price,
                        style = MaterialTheme.typography.caption,
                        color = HermosaHappyHourTheme.colors.brand,
                        modifier = Modifier.weight(0.25f)
                    )
                    Text(
                        text = deal.description,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.weight(0.75f)
                    )
                }
            }
        }
        val specialEvent = if (happyHourEvent?.title.toString() == "Happy Hour") {
            "No Event Today"
        } else {
            happyHourEvent?.title.toString()
        }
        Text(
            text = "Today's Event",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
        Text(
            text = specialEvent,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Text(
            text = "Event Specials",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )
        Text(
            text = "None",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
private fun NextHappyHour(
    restaurant: Restaurant?,
    currentTime: Calendar,
    startTime: Calendar,
    endTime: Calendar,
    stringStart: String?,
    stringEnd: String?,
    detailViewModel: DetailViewModel = viewModel(),
) {
    var popupControl by remember { mutableStateOf(false) }
    Column {
        var millisInFuture = 0L
        var timeIndicatorColor = HermosaHappyHourTheme.colors.textSecondary
        val annotatedTimeString = buildAnnotatedString {
            append("Happy Hour ")
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
            color = HermosaHappyHourTheme.colors.textSecondary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Text(
            text = "See all happy hours \u279C",
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
                RestaurantHours(
                    restaurant = restaurant,
                    onClick = {
                        popupControl = false
                    },
                    isHappyHour = true
                )
            }
        }
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