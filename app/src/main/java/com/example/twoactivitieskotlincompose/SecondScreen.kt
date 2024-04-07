package com.example.twoactivitieskotlincompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SecondScreen(
    message: String?,
    onReply: (String) -> Unit,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = stringResource(R.string.message_received))
        Text(text = message ?: "")
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
        var reply by rememberSaveable { mutableStateOf("") }

        TextField(
            modifier = Modifier.padding(8.dp),
            value = reply,
            onValueChange = {
                reply = it
            },
            placeholder = { Text(stringResource(R.string.reply_placeholder)) },
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = { onReply(reply.ifEmpty { " " }) },
        ) {
            Text(text = stringResource(R.string.reply))
        }
    }
}

@Preview
@Composable
fun PreviewSecondScreen() {
    SecondScreen("Hello") {}
}
