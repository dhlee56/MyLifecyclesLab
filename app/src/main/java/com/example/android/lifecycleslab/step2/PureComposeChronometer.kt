package com.example.android.lifecycleslab.step2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PureComposeChronometer() {
    var timeElapsed by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1.seconds)
            timeElapsed++
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "${timeElapsed/60}:${timeElapsed%60}s", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Hello World!", style = MaterialTheme.typography.headlineMedium)
    }
}
