package com.example.shouldiwearshortstoday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weather = Weather()
        weather.clothingAlgorithm(8,18)

    }
}
