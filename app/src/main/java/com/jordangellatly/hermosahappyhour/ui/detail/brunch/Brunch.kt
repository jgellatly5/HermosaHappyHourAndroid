package com.jordangellatly.hermosahappyhour.ui.detail.brunch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.detail.buildAnnotatedTimerString
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyBrunchScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.detail.getWeeklyHappyHourScheduleFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun Brunch(
    weeklyHours: Map<String, String>,
    annotatedTimeString: AnnotatedString,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        BrunchTimer(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString
        )
        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Brunch Specials",
                specials = specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BrunchPreview() {
    val restaurant = tower12
    val event = saturdayBrunch
    val weeklyHours = getWeeklyBrunchScheduleFromRestaurant(restaurant)
    val annotatedTimeString = buildAnnotatedTimerString(event)
    HermosaHappyHourTheme {
        Brunch(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString,
            specials = event.specials
        )
    }
}