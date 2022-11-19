package com.jordangellatly.hermosahappyhour

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HermosaHappyHourTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RestaurantList(restaurants = generateRestaurants())
                }
            }
        }
    }
}

private fun generateRestaurants(): List<Restaurant> = listOf(
    Restaurant("Tower12"),
    Restaurant("Sharkeez"),
    Restaurant("American Junkie"),
    Restaurant("Henneseys")
)

@Composable
fun RestaurantRow(restaurant: Restaurant, modifier: Modifier = Modifier) {
    Text(text = restaurant.name, modifier = modifier.padding(top = 8.dp, bottom = 8.dp))
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(text = "Restaurant List", style = MaterialTheme.typography.h4)
            }

            items(restaurants) { restaurant ->
                RestaurantRow(restaurant)
                Divider()
            }
        }
    }

}

data class Restaurant(
    val name: String
)

@Preview(showBackground = true)
@Composable
fun RestaurantRowPreview() {
    RestaurantRow(
        Restaurant(name = "Sharkeez")
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HermosaHappyHourTheme {
        RestaurantList(
            restaurants = generateRestaurants()
        )
    }
}