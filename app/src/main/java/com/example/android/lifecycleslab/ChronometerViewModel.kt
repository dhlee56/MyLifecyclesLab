package com.example.android.lifecycleslab

import androidx.lifecycle.ViewModel

class ChronometerViewModel: ViewModel() {
    private var mStartTime: Long? = null

    fun getStartTime(): Long? {
        return mStartTime
    }

    fun setStartTime(startTime: Long?): Unit
    {
        this.mStartTime = startTime
    }
}