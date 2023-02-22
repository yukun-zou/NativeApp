package com.example.shouldiwearshortstoday

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weather = Weather()
        val storage = Storage(this)
        val startEnd = findViewById<RangeSlider>(R.id.slider).values
        val coord = storage.cities.get(storage.currentCity)
        val lat = coord!![0]
        val lon = coord!![1]


        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val weatherResponse = weather.getWeatherAPI(lat.toString(), lon.toString())


                val temperature = weatherResponse.getJSONArray("current_weather").getJSONObject(0).getString("temp")
                val condition = weatherResponse.getJSONArray("current_weather").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description")

                showWeatherPopup(temperature, condition)
            } catch (e: Exception) {
                Log.e("OpenWeatherActivity", "Error retrieving weather data", e)
            }
        }
    }



    private fun showWeatherPopup(temperature: String, condition: String) {
        val weatherView = LayoutInflater.from(this).inflate(R.layout.weather_window, null)
        val weatherWindow = PopupWindow(weatherView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        weatherWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        weatherWindow.isFocusable = true


        weatherWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)

        val temperatureTextView = weatherView.findViewById<TextView>(R.id.temperature_textview)
        val conditionTextView = weatherView.findViewById<TextView>(R.id.condition_textview)
        temperatureTextView.text = "$temperature°C"
        conditionTextView.text = condition
    }
}
