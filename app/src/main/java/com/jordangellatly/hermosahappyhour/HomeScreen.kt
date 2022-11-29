package com.jordangellatly.hermosahappyhour

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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController
) {
    val restaurantViewModel: RestaurantViewModel = viewModel()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
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
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val date = getCurrentDateTime()
                    val dateInString = date.toString("EEEE, MMM d, yyyy")
                    Text(
                        text = dateInString,
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            items(restaurantViewModel.sampleRestaurantData) { restaurant ->
                RestaurantCard(
                    name = restaurant.name,
                    image = restaurant.image
                ) {
                    navController.navigate("detail/${restaurant.name}")
                }
            }
        }
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date = Calendar.getInstance().time

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HermosaHappyHourTheme {
        HomeScreen(
            navController = rememberNavController()
        )
    }
}

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
        backgroundColor = MaterialTheme.colors.surface,
        onClick = onClick
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
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface
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
    RestaurantCard(
        name = "Sharkeez",
        image = R.drawable.sharkeez
    ) {}
}