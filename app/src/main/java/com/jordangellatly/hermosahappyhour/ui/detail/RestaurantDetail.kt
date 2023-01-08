package com.jordangellatly.hermosahappyhour.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.detail.info.EventInfo
import com.jordangellatly.hermosahappyhour.ui.detail.info.GeneralInfo
import com.jordangellatly.hermosahappyhour.ui.detail.happyhour.HappyHour
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
            restaurant.eventsByDate.getValue(formattedDateTimestamp)[EventType.HappyHour]?.let { event ->
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
            EventInfo(weeklyEvents = restaurant.eventsByDate)
            GeneralInfo(restaurant = restaurant)
        }
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