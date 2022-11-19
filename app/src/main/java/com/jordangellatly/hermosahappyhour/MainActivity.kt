package com.jordangellatly.hermosahappyhour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    val navController = rememberNavController()
                    MyAppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(
                navController = navController,
                restaurants = generateRestaurants()
            )
        }
        composable(route = "detail") {
            DetailScreen(
                navController = navController
            )
        }
    }
}

@Composable
fun DetailScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail View") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }
            )
        }
    ) { contentPadding ->
        Text(text = "Hello", modifier = Modifier.padding(contentPadding))
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController())
}

private fun generateRestaurants(): List<Restaurant> = listOf(
    Restaurant("Tower12", "Best karaoke in town!", R.drawable.tower12),
    Restaurant("Sharkeez", "Best margaritas in town!", R.drawable.sharkeez),
    Restaurant("American Junkie", "Best taco tuesday in town!", R.drawable.junkie),
    Restaurant("Henneseys", "Best bloody mary in town!", R.drawable.hennesseys)
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RestaurantCard(
    name: String,
    description: String,
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
fun HomeScreen(
    navController: NavController,
    restaurants: List<Restaurant>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        },
        bottomBar = {
            BottomBar()
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
                RestaurantCard(
                    restaurant.name,
                    restaurant.description,
                    restaurant.image,
                    onClick = {
                        navController.navigate("detail")
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "Home")
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "Search")
            },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = "Favorite")
            },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            }
        )
    }
}

data class Restaurant(
    val name: String,
    val description: String,
    val image: Int
)

@Preview(showBackground = true)
@Composable
fun RestaurantCardPreview() {
    RestaurantCard(
        name = "Sharkeez",
        description = "Best margaritas in town!",
        image = R.drawable.sharkeez,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HermosaHappyHourTheme {
        HomeScreen(
            navController = rememberNavController(),
            restaurants = generateRestaurants()
        )
    }
}