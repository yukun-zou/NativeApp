package com.example.shouldiwearshortstoday

import android.app.AlertDialog
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class CityAdd: AppCompatActivity() {
    private lateinit var storage : Storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addcitypage)
        storage = Storage(this)
    }

    fun closeSettingsActivity(view: View) {
        finish()
    }

    fun openCityActivity(view: View) {
        val intent = Intent(this, CityActivity::class.java)
        startActivity(intent)
    }

    fun ConfirmActivity(view: View){
        val editText = findViewById<EditText>(R.id.edittext)
        if(getCityCoordinates(editText.text.toString())==null){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Warning")
            builder.setMessage("The cityname is spilt wrong, please retry")
            builder.setPositiveButton("OK") { dialog, which ->
            }
            val dialog = builder.create()
            dialog.show()
        }
        else{
            storage.addCity(editText.text.toString(),getCityCoordinates(editText.text.toString())!!.first.toInt(),getCityCoordinates(editText.text.toString())!!.second.toInt())
            val builder = AlertDialog.Builder(this)
            builder.setTitle("reminding")
            builder.setMessage("Adding success")
            builder.setPositiveButton("OK") { dialog, which ->
                val intent = Intent(this, CityActivity::class.java)
                startActivity(intent)
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    fun getCityCoordinates(cityName: String): Pair<Double, Double>? {
        val geocoder = Geocoder(this)
        val addresses = geocoder.getFromLocationName(cityName, 1)

        if (addresses.isNotEmpty()) {
            val latitude = addresses[0].latitude
            val longitude = addresses[0].longitude
            return Pair(latitude, longitude)
        } else {
            return null
        }
    }

}