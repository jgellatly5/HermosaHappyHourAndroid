package com.jordangellatly.hermosahappyhour.ui.detail.special

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.sundaySilentDiscoSunset
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun SpecialEvent(
    weeklyHoursDescription: String,
    eventStart: String,
    eventEnd: String,
    eventTitle: String,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        SpecialEventTimer(
            weeklyHoursDescription = weeklyHoursDescription,
            eventStart = eventStart,
            eventEnd = eventEnd,
            eventTitle = eventTitle
        )
        SpecialEventRow(
            title = "Drink Specials",
            onClick = {}
        )
        SpecialEventRow(
            title = "Food Specials",
            onClick = {}
        )
    }
}

@Composable
private fun SpecialEventRow(
    title: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        IconButton(onClick = onClick) {
            when (title) {
                "Drink Specials",
                "Food Specials" -> {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = title
                    )
                }
            }
        }
    }
    HappyHourDivider()
}

@Preview(showBackground = true)
@Composable
private fun SpecialEventPreview() {
    val event = sundaySilentDiscoSunset
    HermosaHappyHourTheme {
        SpecialEvent(
            weeklyHoursDescription = event.weeklyHoursDescription,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            eventTitle = event.title,
            specials = event.specials
        )
    }
}