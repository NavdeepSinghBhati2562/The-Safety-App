package com.example.thesafetyapp


import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class Home : AppCompatActivity() {



    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION_AND_SEND_SMS = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationAndSMSPermission()



        alertBtn.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this@Home,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(this@Home,
                    android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this@Home,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest
                        .permission.SEND_SMS),
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION_AND_SEND_SMS)

            }else{

                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.

                        if (location != null) {
                            //logic handle location
                            var latitude: Double = location.latitude
                            var longitude: Double = (location.longitude)

                            //getting current Location Address
                            var curLoc : String = getCompAddress(latitude,longitude).toString()
                            //SENDING TEXT SMS OF ALERT WITH CURRENT ADDRESS
                            sendTextMessage(curLoc)
                        }
                    }
            }

        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.three_button_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intentEditclass = Intent(this@Home,EditActivity::class.java)


        when(item?.itemId)
        {
            R.id.settingBtn ->
                startActivity(intentEditclass)
            R.id.aboutusBtn ->
                Toast.makeText(this,"about us",Toast.LENGTH_LONG).show()
            R.id.helpBtn ->
                Toast.makeText(this,"help",Toast.LENGTH_LONG).show()

        }


        return super.onContextItemSelected(item)
    }

    private fun locationAndSMSPermission() {

        if (ContextCompat.checkSelfPermission(this@Home,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED&&
            ContextCompat.checkSelfPermission(this@Home,
                android.Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?

            if (ActivityCompat.shouldShowRequestPermissionRationale(this@Home,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)&&
                ActivityCompat.shouldShowRequestPermissionRationale(this@Home,
                    android.Manifest.permission.SEND_SMS)) {

                // after clicking DENY
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//                AlertDialog.Builder(this)
//                    .setTitle("Required Location")
//                    .setMessage("You Have to give your location for your safety")
//                    .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, which ->
//                        ActivityCompat.requestPermissions(
//                            this@Home,
//                            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
//                            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION
//                        )
//                    })
//                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
//
//                        dialog.dismiss()
//                    })
//                    .create()
//                    .show()

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this@Home,
                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest
                        .permission.SEND_SMS),
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION_AND_SEND_SMS)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else { // Permission has already been granted

            }

//            ActivityCompat.OnRequestPermissionsResultCallback { requestCode, permissions, grantResults ->
//                if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION) {
//                    if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    } else {
//
//                    }
//                }
//            }
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

    protected fun sendTextMessage(currentLoc : String) {
        val db = DataBaseHandler(applicationContext)
        var data = db.readableData()
        var sendPhone1 : String = ""
        var sendPhone2 : String = ""
        var sendPhone3 : String = ""
        sendPhone1=data.get(0).phone1
        sendPhone2=data.get(0).phone2
        sendPhone3=data.get(0).phone3
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(sendPhone1, null, "Please Help me, I am Cautious \n I am here : "+currentLoc, null, null)
        smsManager.sendTextMessage(sendPhone2, null, "Please Help me, I am Cautious \n I am here : "+currentLoc, null, null)
        smsManager.sendTextMessage(sendPhone3, null, "Please Help me, I am Cautious \n I am here : "+currentLoc, null, null)

        Toast.makeText(this@Home, "Sent", Toast.LENGTH_SHORT).show()
    }

//    private fun gettingSMSpermission() {
//        //check if the permission is not granted
//        if (ContextCompat.checkSelfPermission(this@Home, android.Manifest.permission.SEND_SMS)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    this@Home, android.Manifest.permission.SEND_SMS)) {
//            } else {
//                // a pop up will appear asking for required permission i.e aloow or deny
//                ActivityCompat.requestPermissions(
//                    this@Home,
//                    arrayOf(android.Manifest.permission.SEND_SMS),
//                    MY_PERMISSION_REQUEST_SEND_SMS
//                )
//            }
//        }
//    }
//
//
//
//    @Override
//    fun onRequestPermission(requestCode: Int, permission: Array<String?>?, grantResult: IntArray
//    ) {
//        //willl check the request code
//        when (requestCode) {
//            MY_PERMISSION_REQUEST_SEND_SMS ->
//                //check whether the result of grant resukt is greater than 0 and equal to PERMISSON GRANTED
//                if (grantResult.size > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this@Home, "Thanks for permitting", Toast.LENGTH_SHORT).show()
//                }
//            else{
//                    Toast.makeText(this@Home,"You idiot",Toast.LENGTH_LONG).show()
//                }
//        } //method
//    }


   }

