package com.example.android.lifecycleslab.step7

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SavedStateViewModel: ViewModel() {
    var _name: MutableLiveData<String> = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name
    fun saveNewName(name: String) {
        _name.value = name
    }
}