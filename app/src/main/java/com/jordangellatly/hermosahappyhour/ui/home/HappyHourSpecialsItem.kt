package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Deal
import com.jordangellatly.hermosahappyhour.ui.components.DealImage
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun HappyHourSpecialsItem(
    deal: Deal,
    onDealClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(
            start = 4.dp,
            end = 4.dp,
            bottom = 8.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = { /*TODO*/ })
                .padding(8.dp)
        ) {
            DealImage(
                description = deal.description,
                modifier = Modifier.size(120.dp),
                elevation = 4.dp,
            )
            Text(
                text = deal.price,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.brand,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun HappyHourSpecialsItemPreview() {
    HermosaHappyHourTheme {
        val deal = Deal(
            description = "Shots, bottled & can beers",
            price = "50% off"
        )
        HappyHourSpecialsItem(
            deal = deal,
            onDealClick = {}
        )
    }
}