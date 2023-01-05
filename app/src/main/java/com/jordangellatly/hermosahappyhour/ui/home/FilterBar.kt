package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.R
import com.jordangellatly.hermosahappyhour.model.EventType
import com.jordangellatly.hermosahappyhour.model.Filter
import com.jordangellatly.hermosahappyhour.model.filters
import com.jordangellatly.hermosahappyhour.ui.components.diagonalGradientBorder
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun FilterBar(
    filters: SnapshotStateList<Filter>,
    onFilterClick: (Filter) -> Unit,
    onShowFilterPopup: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .heightIn(min = 56.dp)
            .horizontalScroll(rememberScrollState())
    ) {
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
        val selectedType = remember { mutableStateOf(EventType.HappyHour) }
        FilterChipHappyHour(
            filter = filters[0],
            selectedType = selectedType,
            filters = filters,
            onFilterClick = onFilterClick,
            shape = MaterialTheme.shapes.small
        )
        FilterChipBrunch(
            filter = filters[1],
            selectedType = selectedType,
            filters = filters,
            onFilterClick = onFilterClick,
            shape = MaterialTheme.shapes.small
        )
        FilterChipSports(
            filter = filters[2],
            selectedType = selectedType,
            filters = filters,
            onFilterClick = onFilterClick,
            shape = MaterialTheme.shapes.small
        )
        FilterChipSpecial(
            filter = filters[3],
            selectedType = selectedType,
            filters = filters,
            onFilterClick = onFilterClick,
            shape = MaterialTheme.shapes.small
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterBarPreview() {
    HermosaHappyHourTheme {
        FilterBar(
            filters = filters,
            onFilterClick = {},
            onShowFilterPopup = {}
        )
    }
}