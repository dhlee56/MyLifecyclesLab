package com.example.android.lifecycleslab.step2

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