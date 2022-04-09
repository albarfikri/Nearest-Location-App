package com.albar.nearestlocation.view.activities

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.albar.nearestlocation.R
import com.albar.nearestlocation.data.model.details.ModelDetail
import com.albar.nearestlocation.databinding.ActivityRuteBinding
import com.albar.nearestlocation.view.activities.MainActivity.Companion.setWindowFlag
import com.albar.nearestlocation.viewmodel.MainViewModel
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.activity_rute.*

class RuteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRuteBinding
    lateinit var progressDialog: ProgressDialog
    lateinit var mainViewModel: MainViewModel
    lateinit var simpleLocation: SimpleLocation
    lateinit var strPlaceId: String
    lateinit var strNamaLokasi: String
    lateinit var strNamaJalan: String
    lateinit var strRating: String
    lateinit var strPhone: String
    lateinit var fromLatLng: LatLng
    lateinit var toLatlng: LatLng
    lateinit var strCurrentLocation: String
    var strCurrentLatitude = 0.0
    var strLatitude = 0.0
    var strCurrentLongitude = 0.0
    var strLongitude = 0.0
    var strOpenHour: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRuteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("sedang menampilkan detail rute")

        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        simpleLocation = SimpleLocation(this)
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        //get location
        strCurrentLatitude = simpleLocation.latitude
        strCurrentLongitude = simpleLocation.longitude

        //set Location lat long
        strCurrentLocation = "$strCurrentLatitude, $strCurrentLongitude"
        val supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        //get data intent from adapter
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            strPlaceId = bundle["placeId"] as String
            strLatitude = bundle["lat"] as Double
            strLongitude = bundle["lng"] as Double
            strNamaJalan = bundle["vicinity"] as String

            // latLong origin & Destination
            fromLatLng = LatLng(strCurrentLatitude, strCurrentLongitude)
            toLatlng = LatLng(strLatitude, strLongitude)

            // viewModel
            mainViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(mainViewModel::class.java)
            mainViewModel.setDetailLocation(strPlaceId)
            progressDialog.show()
            mainViewModel.getDetailLocation().observe(this) { modelDetail: ModelDetail ->
                strNamaLokasi = modelDetail.name
                strPhone = modelDetail.formatted_phone_number.toString()
                strRating = java.lang.String.valueOf(modelDetail.rating)

                //rating
                val newValue = modelDetail.rating.toFloat()
                ratingBar.setNumStars(5)
                ratingBar.setStepSize(0.5.toFloat())
                ratingBar.setRating(newValue)

                //set text detail location
                tvNamaLokasi.setText(strNamaLokasi)
                tvNamaJalan.setText(strNamaJalan)

                if (strRating == "0.0") {
                    tvRating.setText("Tempat belum memiliki rating !")
                } else {
                    tvRating.setText(strRating)
                }

                //jam operasional
                try {
                    strOpenHour = modelDetail.modelOpening.weekdayText
                    if (strOpenHour != null) {
                        val stringBuilder = StringBuilder()
                        for (strList in strOpenHour) {
                            stringBuilder.append(strList + "\n\n")
                        }
                        tvJamOperasional.text = "Jam Operasional :"
                        tvJamOperasional.setTextColor(Color.BLACK)
                        tvJamBuka.text = stringBuilder.toString()
                        tvJamBuka.setTextColor(Color.BLACK)
                        imageTime.setBackgroundResource(R.drawable.ic_time)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    tvJamOperasional.text = "Bengkel Tutup Sementara"
                    tvJamOperasional.setTextColor(Color.RED)
                    tvJamBuka.visibility = View.GONE
                }

                // Intent open Google Maps
                llRoute.setOnClickListener {
                    val intent = Intent(
                        Intent.ACTION.VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=$strLatitude,$strLongitude")
                    )
                    startActivity(intent)
                }
            }
        }
    }
}