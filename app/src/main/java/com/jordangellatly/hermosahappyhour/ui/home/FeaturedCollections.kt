package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.model.*
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringIcon

@Composable
fun FeaturedRestaurantCollection(
    restaurantCollection: RestaurantCollection,
    onRestaurantClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    index: Int = 0
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = restaurantCollection.name,
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = mirroringIcon(
                        ltrIcon = Icons.Outlined.ArrowForward,
                        rtlIcon = Icons.Outlined.ArrowBack
                    ),
                    tint = HermosaHappyHourTheme.colors.brand,
                    contentDescription = null
                )
            }
        }
        when (restaurantCollection.type) {
            CollectionType.HappyHour -> {
                EventCollection(
                    index,
                    restaurantCollection.restaurants,
                    onRestaurantClick,
                    type = CollectionType.HappyHour
                )
            }
            CollectionType.Event -> {
                EventCollection(
                    index,
                    restaurantCollection.restaurants,
                    onRestaurantClick,
                    type = CollectionType.Event
                )
            }
            CollectionType.Normal -> {
                HappyHourCollection(restaurantCollection.restaurants, onRestaurantClick)
            }
        }
    }
}

@Preview
@Composable
fun FeaturedRestaurantCollectionPreview() {
    HermosaHappyHourTheme {
        val restaurantCollection = RestaurantCollection(
            id = 1,
            name = "Featured Happy Hours",
            restaurants = sampleSearchRestaurantData,
            type = CollectionType.Normal
        )
        FeaturedRestaurantCollection(
            restaurantCollection = restaurantCollection,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun FeaturedSpecialsCollection(
    specialsCollection: SpecialsCollection,
    onDealClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(start = 24.dp)
        ) {
            Text(
                text = specialsCollection.name,
                style = MaterialTheme.typography.h6,
                color = HermosaHappyHourTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            IconButton(
                onClick = { /* todo */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = mirroringIcon(
                        ltrIcon = Icons.Outlined.ArrowForward,
                        rtlIcon = Icons.Outlined.ArrowBack
                    ),
                    tint = HermosaHappyHourTheme.colors.brand,
                    contentDescription = null
                )
            }
        }
        HappyHourSpecialsCollection(
            specials = specialsCollection.specials,
            onDealClick = {/*TODO*/ }
        )
    }
}

@Preview
@Composable
fun FeaturedSpecialsCollectionPreview() {
    HermosaHappyHourTheme {
        val specialsCollection = SpecialsCollection(
            id = 1L,
            name = "Featured Specials",
            specials = tower12HappyHour.specials
        )
        FeaturedSpecialsCollection(
            specialsCollection = specialsCollection,
            onDealClick = {}
        )
    }
}