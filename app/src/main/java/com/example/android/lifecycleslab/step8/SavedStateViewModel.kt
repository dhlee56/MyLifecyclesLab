package com.example.android.lifecycleslab.step8

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    //var _name: MutableLiveData<String> = MutableLiveData<String>()
    //val name: MutableLiveData<String> = _name
    val NAME_KEY = "name"
    val mState = savedStateHandle
    val name = mState.getLiveData<String?>(NAME_KEY)
    fun saveNewName(name: String) {
        mState.set(NAME_KEY, name);
    }
}