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
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.detail.info.EventInfo
import com.jordangellatly.hermosahappyhour.ui.detail.info.GeneralInfo
import com.jordangellatly.hermosahappyhour.ui.detail.info.HappyHourInfo
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.theme.Neutral8
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringBackIcon

@Composable
fun RestaurantDetail(
    restaurantId: Long,
    upPress: () -> Unit
) {
    val restaurant = remember(restaurantId) { RestaurantRepo.getRestaurant(restaurantId) }
    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        },
        backgroundColor = HermosaHappyHourTheme.colors.uiBackground
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding)
        ) {
            Header(restaurant = restaurant, upPress = upPress)
            HappyHourInfo(restaurant = restaurant)
            EventInfo(restaurant = restaurant)
            GeneralInfo(restaurant = restaurant)
        }
    }
}

@Composable
private fun Header(
    restaurant: Restaurant?,
    upPress: () -> Unit
) {
    Box {
        Image(
            painter = painterResource(
                id = restaurant?.image ?: R.drawable.tower12
            ),
            contentDescription = null,
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
            text = restaurant?.name.toString(),
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