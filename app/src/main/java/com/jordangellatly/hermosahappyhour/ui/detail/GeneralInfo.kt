package com.jordangellatly.hermosahappyhour.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.Location
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.tower12RestaurantData
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun GeneralInfo(restaurant: Restaurant?) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        val date = getCurrentDateTime()
        val getDayFromDate = date.toString("EEEE").uppercase()
        val hoursAndEventsToday =
            restaurant?.hoursAndSpecials?.find { it.dayOfWeek.toString() == getDayFromDate }
        val happyHourEvent = hoursAndEventsToday?.specialEvents?.find { it.title == "Happy Hour" }
        val happyHours = happyHourEvent?.hours
        var popupControl by remember { mutableStateOf(false) }
        var isHappyHour by remember { mutableStateOf(false) }
        Text(
            text = "Info",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Hours",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = hoursAndEventsToday?.businessHours.toString(),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    popupControl = true
                    isHappyHour = false
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "hours"
                )
            }
        }
        HappyHourDivider()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Happy Hour",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = happyHours.toString(),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    popupControl = true
                    isHappyHour = true
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.beer),
                    contentDescription = "happy hour"
                )
            }
        }
        HappyHourDivider()

        val context = LocalContext.current
        val website = restaurant?.website.toString()
        val openWebsiteIntent =
            remember { Intent(Intent.ACTION_VIEW, Uri.parse(website)) }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Website",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = website,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    context.startActivity(openWebsiteIntent)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.open_in_new),
                    contentDescription = "website"
                )
            }
        }
        HappyHourDivider()

        val phoneNumber = restaurant?.phoneNumber ?: ""
        val callNumberIntent = remember { Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")) }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Call",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = phoneNumber,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    context.startActivity(callNumberIntent)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "phone"
                )
            }
        }
        HappyHourDivider()

        val addressLine1 = restaurant?.address?.line1 ?: ""
        val addressLine2 = restaurant?.address?.line2 ?: ""
        val fullAddress = addressLine1 + addressLine2
        val getDirectionsIntent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=$fullAddress"))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Get Directions",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = addressLine1,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = addressLine2,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    context.startActivity(getDirectionsIntent)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "get directions"
                )
            }
        }
        HappyHourDivider()

        val location = restaurant?.location ?: Location(0.0, 0.0)
        val latlng = LatLng(location.latitude, location.longitude)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latlng, 16f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = latlng),
                title = restaurant?.name
            )
        }

        if (popupControl) {
            Popup(
                onDismissRequest = { popupControl = false }
            ) {
                RestaurantHours(
                    restaurant = restaurant,
                    onClick = {
                        popupControl = false
                    },
                    isHappyHour = isHappyHour
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    HermosaHappyHourTheme {
        GeneralInfo(
            restaurant = tower12RestaurantData
        )
    }
}