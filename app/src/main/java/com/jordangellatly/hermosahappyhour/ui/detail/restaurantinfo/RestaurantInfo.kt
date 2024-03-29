package com.jordangellatly.hermosahappyhour.ui.detail.restaurantinfo

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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.Location
import com.jordangellatly.hermosahappyhour.model.Restaurant
import com.jordangellatly.hermosahappyhour.model.tower12
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.home.getCurrentDateTime
import com.jordangellatly.hermosahappyhour.ui.home.toString
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun RestaurantInfo(restaurant: Restaurant) {
    Column(modifier = Modifier.padding(8.dp)) {
        val date = getCurrentDateTime()
        var popupControl by remember { mutableStateOf(false) }
        Text(
            text = "Info",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )

        val getDayFromDate = date.toString("EEEE").uppercase()
        InfoRow(
            title = "Hours",
            description = restaurant.weeklyHours.getValue(getDayFromDate),
            onClick = { popupControl = true }
        )

        val context = LocalContext.current
        val website = restaurant.website
        val openWebsiteIntent =
            remember { Intent(Intent.ACTION_VIEW, Uri.parse(website)) }
        InfoRow(
            title = "Website",
            description = website,
            onClick = {
                context.startActivity(openWebsiteIntent)
            }
        )

        val phoneNumber = restaurant.phoneNumber
        val callNumberIntent =
            remember { Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")) }
        InfoRow(
            title = "Call",
            description = phoneNumber,
            onClick = {
                context.startActivity(callNumberIntent)
            }
        )

        val addressLine1 = restaurant.address.line1
        val addressLine2 = restaurant.address.line2
        val fullAddress = addressLine1 + addressLine2
        val displayAddress = "$addressLine1 \n$addressLine2"
        val getDirectionsIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=$fullAddress"))
        InfoRow(
            title = "Get Directions",
            description = displayAddress,
            onClick = {
                context.startActivity(getDirectionsIntent)
            }
        )

        BottomMap(restaurant = restaurant)

        if (popupControl) {
            val title = "Hours"
            val hours = restaurant.weeklyHours
            HoursPopup(
                title = title,
                weeklyHours = hours,
                onClick = { popupControl = false },
                onDismissRequest = { popupControl = false }
            )
        }
    }
}

@Composable
private fun InfoRow(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Text(
                text = description,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                lineHeight = if (title == "Get Directions") 20.sp else TextUnit.Unspecified
            )
        }
        IconButton(onClick = onClick) {
            when (title) {
                "Hours" -> {
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = title
                    )
                }
                "Website" -> {
                    Icon(
                        painter = painterResource(id = R.drawable.open_in_new),
                        contentDescription = title
                    )
                }
                "Call" -> {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = title
                    )
                }
                "Get Directions" -> {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = title
                    )
                }
            }
        }
    }
    HappyHourDivider()
}

@Composable
private fun BottomMap(restaurant: Restaurant?) {
    val location = restaurant?.location ?: Location(0.0, 0.0)
    val latlng = LatLng(location.latitude, location.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlng, 16f)
    }
    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                LocalContext.current,
                R.raw.style_json
            )
        )
    ) {
        Marker(
            state = MarkerState(position = latlng),
            title = restaurant?.name
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantInfoPreview() {
    HermosaHappyHourTheme {
        RestaurantInfo(restaurant = tower12)
    }
}