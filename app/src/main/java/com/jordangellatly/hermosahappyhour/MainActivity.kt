package com.jordangellatly.hermosahappyhour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    Restaurant("Tower12", "Best karaoke in town!", R.drawable.tower12),
    Restaurant("Sharkeez", "Best margaritas in town!", R.drawable.sharkeez),
    Restaurant("American Junkie", "Best taco tuesday in town!", R.drawable.junkie),
    Restaurant("Henneseys", "Best bloody mary in town!", R.drawable.hennesseys)
)

@Composable
fun RestaurantCard(
    name: String,
    description: String,
    image: Int
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 25.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Restaurant List",
                        style = MaterialTheme.typography.h3
                    )
                }
            }
            items(restaurants) { restaurant ->
                RestaurantCard(restaurant.name, restaurant.description, restaurant.image)
            }
        }
    }

}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int
)

@Preview(showBackground = true)
@Composable
fun RestaurantRowPreview() {
    RestaurantCard(
        name = "Sharkeez",
        description = "Best margaritas in town!",
        image = R.drawable.sharkeez
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