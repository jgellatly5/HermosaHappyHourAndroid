package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.model.RestaurantCollection
import com.jordangellatly.hermosahappyhour.model.RestaurantRepo
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun Feed(
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val restaurantCollections = remember { RestaurantRepo.getRestaurants() }
    val filters = remember { RestaurantRepo.getFilters() }
    Feed(
        restaurantCollections,
        filters,
        onRestaurantClick,
        modifier
    )
}

@Composable
private fun Feed(
    restaurantCollections: List<RestaurantCollection>,
    filters: List<Filter>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        Box {
            RestaurantCollectionList(restaurantCollections, filters, onRestaurantClick)
            DateBar()
        }
    }
}

@Composable
private fun RestaurantCollectionList(
    restaurantCollections: List<RestaurantCollection>,
    filters: List<Filter>,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var filtersVisible by rememberSaveable { mutableStateOf(false) }
    Box(modifier) {
        LazyColumn {
            item {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
//                FilterBar(filters, onShowFilters = { filtersVisible = true })
            }
            itemsIndexed(restaurantCollections) { index, restaurantCollection ->
                if (index > 0) {
                    HappyHourDivider(thickness = 2.dp)
                }

                FeaturedCollection(
                    restaurantCollection = restaurantCollection,
                    onRestaurantClick = onRestaurantClick,
                    index = index
                )
            }
        }
    }
    AnimatedVisibility(
        visible = filtersVisible,
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
//        FilterScreen(
//            onDismiss = { filtersVisible = false }
//        )
    }
}

@Preview
@Composable
fun HomePreview() {
    HermosaHappyHourTheme {
        Feed(onRestaurantClick = { })
    }
}