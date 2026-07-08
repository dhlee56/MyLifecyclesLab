package com.example.android.lifecycleslab.step3

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.lifecycle.Observer
import com.example.android.lifecycleslab.R
import com.example.android.lifecycleslab.step2.ui.theme.AndroidLifecyclesLabTheme

class ChronoActivity3 : ComponentActivity() {
    val timerViewModel: LiveDataTimerViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidLifecyclesLabTheme {
                val elapsedTime by timerViewModel.getElapsedTime().observeAsState()
                var eTimeLong by remember { mutableStateOf(0L) }
                fun subscribe() {
                    val elapsedTimeObserver: Observer<Long?> = object : Observer<Long?> {
                        override fun onChanged(aLong: Long?) {
                            val newText: String? = this@ChronoActivity3.getResources().getString(
                                R.string.seconds, aLong
                            )
                            Log.d("ChronoActivity3", "Updating timer $aLong")
                            eTimeLong = aLong!!
                        }
                    }

                    //TODO: observe the ViewModel's elapsed time
                    timerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
                }
                LaunchedEffect(Unit) {
                    subscribe()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Chronometer") },
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(color = Green),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        Text(" Hello World!")
                        Text(" $elapsedTime seconds elapsed")
                        Text(" $eTimeLong seconds elapsed")
                    }
                }
            }
        }
    }

}



