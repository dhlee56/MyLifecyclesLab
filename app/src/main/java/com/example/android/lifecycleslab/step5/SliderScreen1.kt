package com.example.android.lifecycleslab.step5

import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SliderScreen1(viewModel: SeekBarViewModel = viewModel()) {
    val mPosition by viewModel.mPosition.collectAsState()
    Slider(
    value = mPosition,
    onValueChange = { viewModel.savePosition(it) }
    )
}