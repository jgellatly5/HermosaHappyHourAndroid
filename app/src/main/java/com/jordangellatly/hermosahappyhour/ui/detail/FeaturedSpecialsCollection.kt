package com.jordangellatly.hermosahappyhour.ui.detail

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
import com.jordangellatly.hermosahappyhour.model.SpecialsCollection
import com.jordangellatly.hermosahappyhour.model.tower12MondayHappyHour
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme
import com.jordangellatly.hermosahappyhour.ui.utils.mirroringIcon

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
                .padding(start = 8.dp)
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
private fun FeaturedSpecialsCollectionPreview() {
    HermosaHappyHourTheme {
        val specialsCollection = SpecialsCollection(
            id = 1L,
            name = "Featured Specials",
            specials = tower12MondayHappyHour.specials
        )
        FeaturedSpecialsCollection(
            specialsCollection = specialsCollection,
            onDealClick = {}
        )
    }
}