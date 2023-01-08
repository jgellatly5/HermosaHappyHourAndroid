package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.SpecialsCollection
import com.jordangellatly.hermosahappyhour.model.saturdayHappyHour
import com.jordangellatly.hermosahappyhour.model.tower12
import com.jordangellatly.hermosahappyhour.ui.detail.FeaturedSpecialsCollection
import com.jordangellatly.hermosahappyhour.ui.detail.buildAnnotatedTimerString
import com.jordangellatly.hermosahappyhour.ui.detail.getHappyHoursFromRestaurant
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun HappyHour(
    weeklyHours: Map<String, String>,
    annotatedTimeString: AnnotatedString,
    specials: List<Deal>
) {
    Column(modifier = Modifier.padding(8.dp)) {
        HappyHourTimer(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString
        )
        FeaturedSpecialsCollection(
            specialsCollection = SpecialsCollection(
                id = 1L,
                name = "Happy Hour Specials",
                specials = specials
            ),
            onDealClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HappyHourPreview() {
    val restaurant = tower12
    val event = saturdayHappyHour
    val weeklyHours = getHappyHoursFromRestaurant(restaurant)
    val annotatedTimeString = buildAnnotatedTimerString(event)
    HermosaHappyHourTheme {
        HappyHour(
            weeklyHours = weeklyHours,
            annotatedTimeString = annotatedTimeString,
            specials = event.specials
        )
    }
}