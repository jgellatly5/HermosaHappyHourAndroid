package com.jordangellatly.hermosahappyhour.ui.detail.special

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.buildAnnotatedTimerString
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyEventScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.detail.popup.HoursPopup
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun SpecialEventTimer(
    weeklyHours: Map<String, String>,
    annotatedTimeString: AnnotatedString
) {
    var popupControl by remember { mutableStateOf(false) }
    Column {
        Text(
            text = "Special Event",
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
            text = "See all special event hours \u279E",
            fontWeight = FontWeight.Bold,
            color = HermosaHappyHourTheme.colors.brand,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .clickable(onClick = { popupControl = true })
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        if (popupControl) {
            HoursPopup(
                title = "Special Events",
                weeklyHours = weeklyHours,
                onClick = { popupControl = false },
                onDismissRequest = { popupControl = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpecialEventTimerPreview() {
    val restaurant = tower12
    val event = sundaySilentDiscoSunset
    val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Special)
    val annotatedTimeString = buildAnnotatedTimerString(event)
    HermosaHappyHourTheme {
        SpecialEventTimer(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString
        )
    }
}