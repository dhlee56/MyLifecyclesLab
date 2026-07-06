package com.example.android.lifecycleslab

import android.os.SystemClock
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NativeChronometer(chronometerViewModel: ChronometerViewModel = viewModel()) {
    var chronometerState by remember { mutableStateOf<Chronometer?>(null) }

    if (chronometerViewModel.getStartTime() == null) {
        // If the start date is not defined, it's a new ViewModel so set it.
        val startTime = SystemClock.elapsedRealtime()
        chronometerViewModel.setStartTime(startTime)
        chronometerState?.setBase(startTime)
    } else {
        // Otherwise the ViewModel has been retained, set the chronometer's base to the original
        // starting time.
        chronometerState?.setBase(chronometerViewModel.getStartTime()!!)
    }
    chronometerState?.start()
    Column(
        //contentAlignment = Alignment.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(color = White)
    ) {
        AndroidView(
            modifier = Modifier.background(color = White),
            factory = { context ->
                Chronometer(context).apply {
                    chronometerState = this
                    textSize = 30f
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                    )
                }
            }
        )
        Text(
            text = "Hello World!",
            style = MaterialTheme.typography.headlineMedium
        )
    }

}