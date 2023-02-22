package com.example.shouldiwearshortstoday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weather = Weather()
        weather.clothingAlgorithm(8,18)
        //val rootView = findViewById<View>(R.id.main_layout)
    }
    fun openSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun openCityActivity(view: View){
        val intent = Intent(this, CityActivity::class.java)
        startActivity(intent)
    }

}
