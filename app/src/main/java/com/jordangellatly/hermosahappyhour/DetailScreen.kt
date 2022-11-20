package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(
    navController: NavController,
    name: String
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
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(contentPadding)
        ) {
            Text(text = name)
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