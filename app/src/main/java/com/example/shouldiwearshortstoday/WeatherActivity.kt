package com.example.shouldiwearshortstoday

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
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
        val coord = storage.cities.get(storage.currentCity)
        val lat = coord!![0]
        val lon = coord!![1]


        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val weatherResponse = weather.getWeatherAPI(lat.toString(), lon.toString())


                val temperature = weatherResponse.getJSONObject("current_weather").getString("temperature")
                val condition = weatherResponse.getJSONObject("current_weather").getInt("weathercode")
                val weatherCondition = wmoInterpreter(condition)
                showWeatherPopup(temperature, weatherCondition)
            } catch (e: Exception) {
                Log.e("OpenWeatherActivity", "Error retrieving weather data", e)
            }
        }
    }


    fun wmoInterpreter(code: Int):String{
        val wmoTable = arrayOf(
            "clear sky",
            "clear sky",
            "mainly clear",
            "overcast",
            "smoke",
            "haze",
            "dust",
            "dust or sand",
            "dust or sand whirl(s)",
            "dust or sand storm in sight",
            "mist",
            "shallow fog",
            "shallow fog",
            "lighning visible, no thunder heard",
            "precipitation in sight",
            "precipitation in sight",
            "precipitation in sight",
            "thunderstorm without precipitation",
            "squalls",
            "funnel cloud(s)",
            "drizzle",
            "rain",
            "snow",
            "rain and snow or ice pellets",
            "freezing drizzle",
            "shower(s) of rain",
            "shower(s) of snow",
            "shower(s) of hail",
            "fog or ice fog",
            "thunderstorm",
            "slight or moderate dust or sand storm (decreasing)",
            "slight or moderate dust or sand storm (no change)",
            "slight or moderate dust or sand storm (increasing)",
            "severe dust or sand storm (decreasing)",
            "severe dust or sand storm (no change)",
            "severe dust or sand storm (increasing)",
            "slight or moderate blowing of snow (below eye level)",
            "heavy drifting snow (below eye level)",
            "slight or moderate blowing of snow (above eye level)",
            "heavy drifting snow (above eye level)",
            "fog or ice fog at a distance",
            "fog or ice fog in patche",
            "fog",
            "fog",
            "fog",
            "fog",
            "fog",
            "fog",
            "depositing rime fog",
            "depositing rime fog",
            "drizzle",
            "light drizzle",
            "moderate drizzle",
            "moderate drizzle",
            "heavy drizzle",
            "heavy drizzle",
            "light freezing drizzle",
            "heavy freezing drizzle",
            "slight drizzle and rain",
            "moderate or heavy drizzle and rain",
            "rain",
            "light rain",
            "moderate rain",
            "moderate rain",
            "heavy rain",
            "heavy rain",
            "light freezing rain",
            "heavy freezing rain",
            "rain or drizzle with slight snow",
            "rain or drizzle with moderate or heavy snow",
            "snow",
            "light snow",
            "moderate snow",
            "moderate snow",
            "heavy snow",
            "heavy snow",
            "ice prisms",
            "snow grains",
            "isolated starlike snow crystals",
            "ice pellets",
            "light rain showers",
            "moderate rain showers",
            "heavy rain showers",
            "showers of rain and snow mixed, slight",
            "howers of rain and snow mixed, moderate or heavy",
            "light snow showers",
            "heavy snow showers",
            "snow pallets",
            "snow pallets",
            "slight showers of hail",
            "moderate or heavy showers of hail",
            "slight rain",
            "moderate or heavy rain",
            "slight precipitation",
            "moderate or heavy precipitation",
            "thunderstorm",
            "thunderstorm with slight hail",
            "heavy thunderstorm",
            "thunderstorm with dust or sand storm",
            "thunderstorm with heavy hail",
        )
        return wmoTable[code]
    }
    private fun showWeatherPopup(temperature: String, condition: String) {
        runOnUiThread {
            val weatherView = LayoutInflater.from(this).inflate(R.layout.weather_window, null)
            val weatherWindow = PopupWindow(
                weatherView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            weatherWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            weatherWindow.isFocusable = true


            weatherWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)

            val temperatureTextView = weatherView.findViewById<TextView>(R.id.temperature_textview)
            val conditionTextView = weatherView.findViewById<TextView>(R.id.condition_textview)
            temperatureTextView.text = "$temperature°C"
            conditionTextView.text = condition.toString()

            val cancelButton = weatherView.findViewById<Button>(R.id.ok_button)
            cancelButton.setOnClickListener {
                finish()
            }
        }
    }

}
