package com.example.android.lifecycleslab.step4

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.core.app.ActivityCompat
import com.example.android.lifecycleslab.step2.ui.theme.AndroidLifecyclesLabTheme

class LocationActivity : ComponentActivity() {
    val REQUEST_LOCATION_PERMISSION_CODE: Int = 1

    val viewModel: LocationViewModel by  viewModels()

    val mGpsListener: MyLocationListener = MyLocationListener()
    fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, getApplicationContext())
    }
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val showRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
                //Manifest.permission.READ_EXTERNAL_STORAGE
            )

        if (grantResults.size > 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //bindLocationListener()
                viewModel.setPermissionsCheck(true)
                Log.d("BoundLocationMgr", "Permission granted")
                //Toast.makeText(this, "Restart the app", Toast.LENGTH_LONG).show()
        } else {
            if( showRationale) Log.d("BoundLocationMgr", "Show rationale ${showRationale}")
            Log.d("BoundLocationMgr", "Permission denied @ result")
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermissions(): Boolean {
        val fineGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarseGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        val allGranted = fineGranted && coarseGranted
        viewModel.setPermissionsCheck(allGranted)
        return allGranted
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AndroidLifecyclesLabTheme {
                val mLocation by viewModel.mLocation.collectAsState()
                val permissionsCheck by viewModel.permissionsCheck.collectAsState()
                val showRationale by viewModel.showRationale.collectAsState()
                LaunchedEffect(permissionsCheck) {
                    if (!permissionsCheck) {
                        Log.d("BoundLocationMgr", "Request Permissions")
                        ActivityCompat.requestPermissions(
                            this@LocationActivity,
                            arrayOf<String>(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ),
                            REQUEST_LOCATION_PERMISSION_CODE
                        )
                    } else {
                        bindLocationListener()
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("LC Step4") },
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
                    ) {
                        if(permissionsCheck) {
                            Text(" Hello World!")
                            Text(" ${mLocation.latitude}, ${mLocation.longitude}")
                        } else {
                            Text("This app requires Location access ${showRationale}")
                        }

                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("BoundLocationMgr", "Resumed")
        if (checkPermissions()) {
            viewModel.setPermissionsCheck(true)
        } else {
            viewModel.setPermissionsCheck(false)
        }
    }
    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            viewModel.saveLocation(location)
        }

        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String) {
            Log.d("BoundLocationMgr", "Provider enabled")
            Toast.makeText(
                this@LocationActivity,
                "Provider enabled: $provider", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onProviderDisabled(provider: String) {
        }
    }

}



