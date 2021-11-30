package com.albar.nearestlocation.view.activities

import android.Manifest
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.albar.nearestlocation.databinding.ActivityMainBinding
import com.google.android.gms.maps.GoogleMap
import im.delight.android.location.SimpleLocation


class MainActivity : AppCompatActivity() {
    lateinit var mapsView: GoogleMap
    lateinit var simpleLocation: SimpleLocation
    private lateinit var binding: ActivityMainBinding
    var permissionArrays = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    lateinit var progressDialog: ProgressDialog
    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter
    lateinit var strCurrentLocation: String
    var strCurrentLatitude = 0.0
    var strCurrentLongitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if(Build.VERSION.SDK_INT >= 21){
            setWindowFlag
        }
    }
}