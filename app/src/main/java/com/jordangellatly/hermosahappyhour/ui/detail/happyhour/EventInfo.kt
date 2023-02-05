package com.jordangellatly.hermosahappyhour.ui.detail.happyhour

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.model.tower12.tower12FridayHappyHour
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.components.RestaurantImage
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun EventInfo(
    weeklyHoursDescription: String,
    eventStart: String,
    eventEnd: String,
    eventTitle: String,
    eventUrl: String,
    drinkSpecials: List<Deal>?,
    foodSpecials: List<Deal>?
) {
    Column(modifier = Modifier.padding(8.dp)) {
        EventHeaderHappyHour(
            weeklyHoursDescription = weeklyHoursDescription,
            eventStart = eventStart,
            eventEnd = eventEnd,
            eventTitle = eventTitle,
            eventUrl = eventUrl
        )
        DrinkSpecials(drinkSpecials = drinkSpecials)
        FoodSpecials(foodSpecials = foodSpecials)
    }
}

@Composable
private fun DrinkSpecials(drinkSpecials: List<Deal>?) {
    Text(
        text = "Drink Specials",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
    if (drinkSpecials != null) {
        drinkSpecials.forEach { deal ->
            DealItem(deal = deal)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "There are no drink specials for this event.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier
                    .width(300.dp)
                    .padding(start = 16.dp, end = 16.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "See Drink Menu",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    color = HermosaHappyHourTheme.colors.textSecondary,
                )
            }
        }
    }
}

@Composable
private fun FoodSpecials(foodSpecials: List<Deal>?) {
    Text(
        text = "Food Specials",
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    )
    if (foodSpecials != null) {
        foodSpecials.forEach { deal ->
            DealItem(deal = deal)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "There are no food specials for this event.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                modifier = Modifier
                    .width(300.dp)
                    .padding(start = 16.dp, end = 16.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "See Food Menu",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    color = HermosaHappyHourTheme.colors.textSecondary,
                )
            }
        }
    }
}

@Composable
fun DealItem(deal: Deal) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Column(modifier = Modifier.weight(3f)) {
            Text(
                text = deal.price,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
            )
            Text(
                text = deal.description,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                lineHeight = 20.sp
            )
        }
        RestaurantImage(
            imageUrl = deal.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .size(80.dp)
                .padding(8.dp)
        )
    }
    HappyHourDivider()
}

@Preview(showBackground = true)
@Composable
private fun EventInfoPreview() {
    val event = tower12FridayHappyHour
    HermosaHappyHourTheme {
        EventInfo(
            weeklyHoursDescription = event.weeklyHoursDescription,
            eventStart = event.startTimestamp,
            eventEnd = event.endTimestamp,
            eventTitle = event.title,
            eventUrl = event.eventInfoUrl,
            drinkSpecials = event.drinkSpecials,
            foodSpecials = event.foodSpecials
        )
    }
}