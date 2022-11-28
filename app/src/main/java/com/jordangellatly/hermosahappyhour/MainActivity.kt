package com.jordangellatly.hermosahappyhour

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                navController = navController
            )
        }
        composable(
            route = "detail/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navController = navController,
                name = navController.currentBackStackEntry?.arguments?.getString("name") ?: ""
            )
        }
    }
}