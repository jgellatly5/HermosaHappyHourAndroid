package com.jordangellatly.hermosahappyhour

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourScaffold
import com.jordangellatly.hermosahappyhour.ui.detail.EventDetail
import com.jordangellatly.hermosahappyhour.viewmodel.EventViewModel
import com.jordangellatly.hermosahappyhour.ui.home.HappyHourBottomBar
import com.jordangellatly.hermosahappyhour.ui.home.HomeSections
import com.jordangellatly.hermosahappyhour.ui.home.addHomeGraph
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class HappyHourApp : Application()

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
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = MainDestinations.HOME_ROUTE,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                happyHourNavGraph(
                    onEventSelected = { eventId, from ->
                        appState.navigateToEventDetail(
                            eventId,
                            from
                        )
                    },
                    upPress = appState::upPress
                )
            }
        }
    }
}

private fun NavGraphBuilder.happyHourNavGraph(
    onEventSelected: (UUID, NavBackStackEntry) -> Unit,
//    onRestaurantSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HOME.route
    ) {
        addHomeGraph(onEventSelected)
    }
    composable(
        route = "${MainDestinations.EVENT_DETAIL_ROUTE}/{${MainDestinations.EVENT_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.EVENT_ID_KEY) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val eventId = UUID.fromString(arguments.getString(MainDestinations.EVENT_ID_KEY))
        val viewModel = hiltViewModel<EventViewModel>()
        EventDetail(
            eventId = eventId,
            upPress = upPress,
            viewModel = viewModel
        )
    }
//    composable(
//        route = "${MainDestinations.RESTAURANT_DETAIL_ROUTE}/{${MainDestinations.RESTAURANT_ID_KEY}}",
//        arguments = listOf(
//            navArgument(MainDestinations.RESTAURANT_ID_KEY) {
//                type = NavType.LongType
//            }
//        )
//    ) { backStackEntry ->
//        val arguments = requireNotNull(backStackEntry.arguments)
//        val restaurantId = arguments.getLong(MainDestinations.RESTAURANT_ID_KEY)
//        RestaurantDetail(
//            restaurantId = restaurantId,
//            upPress = upPress
//        )
//    }
}