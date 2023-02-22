package com.example.shouldiwearshortstoday

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Changingactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changingclothes)
    }

    fun closeChangingactivity(view: View) {
        finish()
    }
}