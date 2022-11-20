package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(
    navController: NavController,
    name: String
) {
    val restaurantViewModel: RestaurantViewModel = viewModel()
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
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(contentPadding)
        ) {
            Image(
                painter = painterResource(
                    id = restaurantViewModel.restaurants.find { it.name == name }?.image
                        ?: R.drawable.tower12
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = restaurantViewModel.restaurants.find { it.name == name }?.description
                        ?: "",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        navController = rememberNavController(),
        name = "Sharkeez"
    )
}