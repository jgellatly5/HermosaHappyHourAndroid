package com.jordangellatly.hermosahappyhour.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.ui.components.HappyHourDivider
import com.jordangellatly.hermosahappyhour.ui.theme.AlphaNearOpaque
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun DateBar(modifier: Modifier = Modifier) {
    Column(modifier = modifier.statusBarsPadding()) {
        TopAppBar(
            backgroundColor = HermosaHappyHourTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
            contentColor = HermosaHappyHourTheme.colors.textSecondary,
            elevation = 0.dp
        ) {
            val date = getCurrentDateTime()
            val dateInString = date.toString("EEEE, MMM d, yyyy")
            Text(
                text = dateInString,
                style = MaterialTheme.typography.subtitle1,
                color = HermosaHappyHourTheme.colors.textSecondary,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
        HappyHourDivider()
    }
}

@Preview
@Composable
fun PreviewDestinationBar() {
    HermosaHappyHourTheme {
        DateBar()
    }
}