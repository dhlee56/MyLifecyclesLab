package com.example.android.lifecycleslab.step5

import android.location.Location
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SeekBarViewModel: ViewModel() {
    var _mPosition: MutableStateFlow<Float> = MutableStateFlow(0f)
    val mPosition: StateFlow<Float> = _mPosition

    fun savePosition(position: Float) {
        _mPosition.value  = position
    }

}