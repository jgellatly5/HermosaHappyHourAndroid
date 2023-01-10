package com.jordangellatly.hermosahappyhour.ui.detail.brunch

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.sundayBrunch
import com.jordangellatly.hermosahappyhour.model.tower12
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyEventScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun Brunch(
    weeklyHours: Map<String, String>,
    eventStart: String,
    eventEnd: String,
    eventTitle: String,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        BrunchTimer(
            weeklyHours = weeklyHours,
            eventStart = eventStart,
            eventEnd = eventEnd,
            eventTitle = eventTitle
        )
        val context = LocalContext.current
        val brunchMenu =
            "https://tower12hb.com/wp-content/uploads/2022/11/Tower-Brunch-8.5x11_11.2022-scaled.jpg"
        val openBrunchMenu = remember { Intent(Intent.ACTION_VIEW, Uri.parse(brunchMenu)) }
        BrunchRow(
            title = "Brunch Menu",
            onClick = {
                context.startActivity(openBrunchMenu)
            }
        )
        val brunchCocktails =
            "https://tower12hb.com/wp-content/uploads/2022/11/Tower-Brunch-drinks_8.5x11_11.2022-scaled.jpg"
        val openBrunchCocktails =
            remember { Intent(Intent.ACTION_VIEW, Uri.parse(brunchCocktails)) }
        BrunchRow(
            title = "Brunch Cocktails",
            onClick = {
                context.startActivity(openBrunchCocktails)
            }
        )
    }
}

@Composable
private fun BrunchRow(
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
                "Brunch Menu",
                "Brunch Cocktails" -> {
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
private fun BrunchPreview() {
    val restaurant = tower12
    val event = sundayBrunch
    val weeklyHours = getWeeklyEventScheduleFromRestaurant(restaurant, EventType.Brunch)
    HermosaHappyHourTheme {
        Brunch(
            weeklyHours = weeklyHours,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            eventTitle = event.title,
            specials = event.specials
        )
    }
}