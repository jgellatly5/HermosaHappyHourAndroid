package com.jordangellatly.hermosahappyhour

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import java.util.*

@Composable
fun SearchScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val textState = remember { mutableStateOf(TextFieldValue("")) }
            SearchBar(state = textState)
            RestaurantList(
                navController = navController,
                state = textState
            )
        }
    }
}

@Composable
fun RestaurantList(
    navController: NavController,
    state: MutableState<TextFieldValue>
) {
    val viewModel = RestaurantViewModel()
    val restaurants = viewModel.sampleSearchRestaurantData
    var filteredRestaurants: List<Restaurant>
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        val searchedText = state.value.text
        filteredRestaurants = if (searchedText.isEmpty()) {
            restaurants
        } else {
            val resultList = ArrayList<Restaurant>()
            for (restaurant in restaurants) {
                if (restaurant.name.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(restaurant)
                }
            }
            resultList
        }
        items(filteredRestaurants) { restaurant ->
            RestaurantCard(
                name = restaurant.name,
                image = restaurant.image
            ) {
                navController.navigate("detail/${restaurant.name}")
            }
        }
    }
}

@Composable
fun SearchBar(
    state: MutableState<TextFieldValue>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            state.value = TextFieldValue("")
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(16.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                leadingIconColor = Color.White,
                trailingIconColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    HermosaHappyHourTheme {
        SearchScreen(
            navController = rememberNavController()
        )
    }
}