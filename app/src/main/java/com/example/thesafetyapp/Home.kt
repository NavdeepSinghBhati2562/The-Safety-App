package com.example.thesafetyapp

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import kotlinx.android.synthetic.main.activity_home.*

import java.util.*

import kotlin.text.StringBuilder

class Home : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()





    }

    private fun fetchLocation() {

        Log.d("navdeep", "HEllo")

        if (ContextCompat.checkSelfPermission(this@Home,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@Home,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                AlertDialog.Builder(this)
                    .setTitle("Required Location")
                    .setMessage("You Have to give your location for your safety")
                    .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
                        ActivityCompat.requestPermissions(
                            this@Home,
                            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
                        )
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

                        dialog.dismiss()
                    })
                    .create()
                    .show()

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this@Home,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted

            alertBtn.setOnClickListener {
                Log.d("navdeep", "HEllo")
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.

                        if (location != null) { //logic handle location
                            var latitude: Double = location.latitude
                            var longitude: Double = (location.longitude)

                            //getting current Location Address
                            myLoc.text =getCompAddress(latitude,longitude)


                        }


                        }


                    }
            }

            ActivityCompat.OnRequestPermissionsResultCallback { requestCode, permissions, grantResults ->
                if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION) {
                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
                }
            }
        }
    //getting address function
    private fun getCompAddress(latitude: Double, longitude: Double): String? {
        var address = ""
        val geocoder = Geocoder(this@Home, Locale.getDefault())
        try {
            val addresses =
                geocoder.getFromLocation(latitude, longitude, 1)
            if (address != null) {
                val returnAddress = addresses[0]
                val stringBuilderReturnAddress =
                    StringBuilder("")
                for (i in 0..returnAddress.maxAddressLineIndex) {
                    stringBuilderReturnAddress.append(returnAddress.getAddressLine(i)).append("\n")
                }
                address = stringBuilderReturnAddress.toString()
            } else {
                Toast.makeText(this@Home, "Address not found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return address
    }



    }

