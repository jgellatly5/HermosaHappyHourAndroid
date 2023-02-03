package com.jordangellatly.hermosahappyhour.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jordangellatly.hermosahappyhour.ui.theme.HermosaHappyHourTheme

@Composable
fun ErrorDialog(message: String) {
//    val openDialog = remember { mutableStateOf(true) }
//    if (openDialog.value) {
//        AlertDialog(
//            onDismissRequest = {
//                openDialog.value = false
//            },
//            title = {
//                Text(text = stringResource(R.string.problem_occurred))
//            },
//            text = {
//                Text(message)
//            },
//            confirmButton = {
//                openDialog.value = false
//            }
//        )
//    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sorry, there was an error.",
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