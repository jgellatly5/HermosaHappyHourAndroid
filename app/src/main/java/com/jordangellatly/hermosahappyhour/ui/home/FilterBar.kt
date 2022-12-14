package com.jordangellatly.hermosahappyhour.ui.home

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.model.filters
import com.jordangellatly.hermosahappyhour.ui.components.HermosaHappyHourSurface
import com.jordangellatly.hermosahappyhour.ui.components.diagonalGradientBorder
import com.jordangellatly.hermosahappyhour.ui.components.fadeInDiagonalGradientBorder
import com.jordangellatly.hermosahappyhour.ui.components.offsetGradientBackground
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun FilterBar(
    filters: SnapshotStateList<Filter>,
    selectedType: MutableState<EventType>,
    onFilterClick: (Filter) -> Unit,
    onShowFilterPopup: () -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 8.dp),
        modifier = Modifier.heightIn(min = 56.dp)
    ) {
        item {
            IconButton(onClick = onShowFilterPopup) {
                Icon(
                    imageVector = Icons.Rounded.FilterList,
                    tint = HermosaHappyHourTheme.colors.brand,
                    contentDescription = stringResource(id = R.string.label_filters),
                    modifier = Modifier.diagonalGradientBorder(
                        colors = HermosaHappyHourTheme.colors.interactiveSecondary,
                        shape = CircleShape
                    )
                )
            }
        }
        items(filters) { filter ->
            FilterChip(
                filter = filter,
                selectedType = selectedType,
                filters = filters,
                onFilterClick = onFilterClick,
                shape = MaterialTheme.shapes.small
            )
        }
    }
}

@Composable
fun FilterChip(
    filter: Filter,
    selectedType: MutableState<EventType>,
    filters: SnapshotStateList<Filter>,
    onFilterClick: (Filter) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small
) {
    val selected = selectedType.value == filter.eventType
    val backgroundColor by animateColorAsState(
        if (selected) HermosaHappyHourTheme.colors.brandSecondary else HermosaHappyHourTheme.colors.uiBackground
    )
    val border = Modifier.fadeInDiagonalGradientBorder(
        showBorder = !selected,
        colors = HermosaHappyHourTheme.colors.interactiveSecondary,
        shape = shape
    )
    val textColor by animateColorAsState(
        if (selected) Color.Black else HermosaHappyHourTheme.colors.textSecondary
    )

    HermosaHappyHourSurface(
        modifier = modifier.height(28.dp),
        color = backgroundColor,
        contentColor = textColor,
        shape = shape,
        elevation = 2.dp
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        val pressed by interactionSource.collectIsPressedAsState()
        val backgroundPressed =
            if (pressed) {
                Modifier.offsetGradientBackground(
                    HermosaHappyHourTheme.colors.interactiveSecondary,
                    200f,
                    0f
                )
            } else {
                Modifier.background(Color.Transparent)
            }
        Box(
            modifier = Modifier
                .toggleable(
                    value = selected,
                    onValueChange = {
                        selectedType.value = filter.eventType
                        onFilterClick(filter)
                        filters[filters.indexOf(filter)] = filter
                    },
                    interactionSource = interactionSource,
                    indication = null
                )
                .then(backgroundPressed)
                .then(border)
        ) {
            Text(
                text = filter.name,
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 6.dp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterBarPreview() {
    HermosaHappyHourTheme {
        FilterBar(
            filters = filters,
            selectedType = remember { mutableStateOf(EventType.HappyHour) },
            onFilterClick = {},
            onShowFilterPopup = {}
        )
    }
}

@Preview("Default")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Large Font", fontScale = 2f)
@Composable
private fun FilterSelectedPreview() {
    HermosaHappyHourTheme {
        FilterChip(
            filter = Filter(
                name = "Happy Hour",
                eventType = EventType.HappyHour
            ),
            selectedType = remember { mutableStateOf(EventType.HappyHour) },
            filters = remember { mutableStateListOf() },
            onFilterClick = {},
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview("Default")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Large Font", fontScale = 2f)
@Composable
private fun FilterNotSelectedPreview() {
    HermosaHappyHourTheme {
        FilterChip(
            filter = Filter(
                name = "Happy Hour",
                eventType = EventType.HappyHour
            ),
            selectedType = remember { mutableStateOf(EventType.Brunch) },
            filters = remember { mutableStateListOf() },
            onFilterClick = {},
        )
    }
}