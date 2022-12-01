package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RestaurantCard(
    name: String,
    image: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = HermosaHappyHourTheme.colors.uiBackground,
        onClick = onClick,
        contentColor = HermosaHappyHourTheme.colors.textPrimary
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Next Happy Hour:",
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Starts at 3PM",
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = "Featured Deal:",
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "Half off shots, bottled & can beers",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantCardPreview() {
    HermosaHappyHourTheme {
        RestaurantCard(
            name = "Sharkeez",
            image = R.drawable.sharkeez
        ) {}
    }
}