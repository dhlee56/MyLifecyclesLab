package com.example.android.lifecycleslab.step4

import android.location.Location
import android.location.LocationManager
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationViewModel: ViewModel() {
    var _mLocation: MutableStateFlow<Location> = MutableStateFlow(Location(""))
    val mLocation: StateFlow<Location> = _mLocation

    val _permissionsCheck: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val permissionsCheck: StateFlow<Boolean> = _permissionsCheck

    fun setPermissionsCheck(check: Boolean) {
        _permissionsCheck.value = check
    }

    fun saveLocation(location: Location) {
        _mLocation.value  = location
    }
}