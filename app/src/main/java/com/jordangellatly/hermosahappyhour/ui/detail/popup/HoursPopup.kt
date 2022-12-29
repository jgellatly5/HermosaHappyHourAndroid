package com.jordangellatly.hermosahappyhour.ui.detail.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.tower12WeeklyHours
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8

@Composable
fun HoursPopup(
    title: String,
    weeklyHours: Map<String, String>,
    onClick: () -> Unit
) {
    HermosaHappyHourSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp)
                )
                Close(
                    onClick = onClick
                )
            }

            weeklyHours.keys.forEach { dayOfWeek ->
                val date = getCurrentDateTime()
                val getDayFromDate = date.toString("EEEE").uppercase()
                val fontWeight =
                    if (getDayFromDate == dayOfWeek) FontWeight.Bold else FontWeight.Normal
                val hoursToday = weeklyHours.getValue(dayOfWeek)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = dayOfWeek.lowercase().replaceFirstChar {
                            it.uppercase()
                        },
                        fontWeight = fontWeight,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = hoursToday,
                        fontWeight = fontWeight,
                        style = MaterialTheme.typography.h6,
                    )
                }
                if (dayOfWeek != "SATURDAY") {
                    HappyHourDivider(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Close(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
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
            imageVector = Icons.Filled.Close,
            tint = HermosaHappyHourTheme.colors.iconInteractive,
            contentDescription = "close"
        )
    }
}

@Preview
@Composable
private fun HoursPopupPreview() {
    HermosaHappyHourTheme {
        HoursPopup(
            title = "Hours",
            weeklyHours = tower12WeeklyHours,
            onClick = {}
        )
    }
}