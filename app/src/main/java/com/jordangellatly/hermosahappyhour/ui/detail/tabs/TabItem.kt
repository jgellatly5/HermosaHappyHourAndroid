package com.jordangellatly.hermosahappyhour.ui.detail.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.jordangellatly.hermosahappyhour.R

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: ImageVector, var title: String, var screen: ComposableFun) {
    object Music : TabItem(Icons.Rounded.Place, "Music", { MusicScreen() })
    object Movies : TabItem(Icons.Rounded.Search, "Movies", { MoviesScreen() })
    object Books : TabItem(Icons.Rounded.Star, "Books", { BooksScreen() })
}
