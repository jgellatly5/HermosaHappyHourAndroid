package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourScaffold
import com.jordangellatly.hermosahappyhour.ui.detail.RestaurantDetail
import com.jordangellatly.hermosahappyhour.ui.home.HappyHourBottomBar
import com.jordangellatly.hermosahappyhour.ui.home.HomeSections
import com.jordangellatly.hermosahappyhour.ui.home.addHomeGraph
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun HermosaHappyHourApp() {
    HermosaHappyHourTheme {
        val appState = rememberHappyHourAppState()
        HappyHourScaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    HappyHourBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
//            snackbarHost = {
//                SnackbarHost(
//                    hostState = it,
//                    modifier = Modifier.systemBarsPadding(),
//                    snackbar = { snackbarData -> JetsnackSnackbar(snackbarData) }
//                )
//            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.HOME_ROUTE,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                happyHourNavGraph(
                    onRestaurantSelected = appState::navigateToRestaurantDetail,
                    upPress = appState::upPress
                )
            }
        }
    }
}

private fun NavGraphBuilder.happyHourNavGraph(
    onRestaurantSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HomeFeed.route
    ) {
        addHomeGraph(onRestaurantSelected)
    }
    composable(
        "${MainDestinations.RESTAURANT_DETAIL_ROUTE}/{${MainDestinations.RESTAURANT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.RESTAURANT_ID_KEY) {
            type = NavType.LongType
        })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val restaurantId = arguments.getLong(MainDestinations.RESTAURANT_ID_KEY)
        RestaurantDetail(restaurantId, upPress)
    }
}