package com.jordangellatly.hermosahappyhour.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jordangellatly.hermosahappyhour.ui.components.ErrorMessage
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.viewmodel.SearchFeedViewModel
import java.util.*

@Composable
fun SearchFeed(
    onRestaurantClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchFeedViewModel = viewModel()
) {
    HermosaHappyHourSurface(modifier = modifier.fillMaxSize()) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        Column {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            SearchBar(state = textState)
            RestaurantList(
                onRestaurantClick = onRestaurantClick,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun RestaurantList(
    onRestaurantClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchFeedViewModel = viewModel()
) {
    Box(modifier) {
        Column {
            when (val state = viewModel.uiState.collectAsState().value) {
                is SearchFeedViewModel.SearchFeedUiState.Empty -> {
                    EmptyStateMessage()
                }
                is SearchFeedViewModel.SearchFeedUiState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is SearchFeedViewModel.SearchFeedUiState.Error -> {
                    ErrorMessage(state.message)
                }
                is SearchFeedViewModel.SearchFeedUiState.Loaded -> {
                    LazyColumn(
                        modifier = modifier,
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
                    ) {
                        items(state.restaurants) { restaurant ->
                            RestaurantItem(restaurant, onRestaurantClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyStateMessage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry, we could not find any restaurants with that search.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            color = HermosaHappyHourTheme.colors.textSecondary,
            modifier = Modifier
                .width(300.dp)
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Preview
@Composable
private fun RestaurantFeedPreview() {
    HermosaHappyHourTheme {
        SearchFeed(onRestaurantClick = { })
    }
}