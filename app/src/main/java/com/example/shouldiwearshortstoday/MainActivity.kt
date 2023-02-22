package com.example.shouldiwearshortstoday

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View

import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val storage = Storage(this)
        val weather = Weather()
        getValues(storage)
        getWeather(weather, storage)
        val slider = findViewById<RangeSlider>(R.id.slider)
        slider.addOnChangeListener(RangeSlider.OnChangeListener { slider, value, fromUser ->
            getWeather(
                weather,
                storage
            )
        })
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWeather(weather: Weather, storage: Storage) {
        val weatherCoroutine = lifecycleScope.async {
            val startEnd = findViewById<RangeSlider>(R.id.slider).values
            val coord = storage.cities.get(storage.currentCity)
            val lat = coord!![0]
            val long = coord!![1]
            weather.clothingAlgorithm(startEnd[0].toInt(), startEnd[1].toInt(), lat, long, storage)
        }
        weatherCoroutine.invokeOnCompletion {
            setClothing(weatherCoroutine.getCompleted())
        }
    }

    fun getValues(storage: Storage) {
        storage.getValuesFromStorage()
        val slider = findViewById<RangeSlider>(R.id.slider)
        slider.values = listOf(storage.defaultStart.toFloat(), storage.defaultEnd.toFloat())
        findViewById<TextView>(R.id.currentCity).text = storage.currentCity
        updateNavbar(storage)
    }

    fun setClothingToInvisible(): Array<ImageView> {
        val clothes = arrayOf(
            findViewById<ImageView>(R.id.hat),
            findViewById<ImageView>(R.id.scarf),
            findViewById<ImageView>(R.id.tshirt),
            findViewById<ImageView>(R.id.hoodie),
            findViewById<ImageView>(R.id.winterJacket),
            findViewById<ImageView>(R.id.shorts),
            findViewById<ImageView>(R.id.trousers),
            findViewById<ImageView>(R.id.umbrella)
        )
        for (element in clothes) {
            element.visibility = View.INVISIBLE
        }
        return clothes
    }

    fun setClothing(weather: Array<String>) {
        val umbrella = weather[1]
        val tempType = weather[0]
        val clothes = setClothingToInvisible()
        if (tempType == "freezing") {
            clothes[0].visibility = View.VISIBLE
            clothes[1].visibility = View.VISIBLE
            clothes[4].visibility = View.VISIBLE
            clothes[6].visibility = View.VISIBLE
        } else if (tempType == "cold") {
            clothes[4].visibility = View.VISIBLE
            clothes[6].visibility = View.VISIBLE
        } else if (tempType == "comfortable") {
            clothes[3].visibility = View.VISIBLE
            clothes[6].visibility = View.VISIBLE
        } else if (tempType == "warm") {
            clothes[2].visibility = View.VISIBLE
            clothes[6].visibility = View.VISIBLE
        } else {
            clothes[2].visibility = View.VISIBLE
            clothes[5].visibility = View.VISIBLE
        }
        if (umbrella == "true") {
            clothes[7].visibility = View.VISIBLE
        }
    }

    fun openSettingsActivity(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun openWeatherActivity(view: View) {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }

        fun updateNavbar(storage: Storage) {
            val currentCityIndex = storage.cities.keys.indexOf(storage.currentCity)
            val citiesSize = storage.cities.size
            var currentCityIndicator = ""
            for (i in 0 until citiesSize) {
                if (i == currentCityIndex)
                    currentCityIndicator += "x"
                else
                    currentCityIndicator += "-"
            }
            findViewById<TextView>(R.id.cityIndicator).text = currentCityIndicator
        }

        fun openChangingclothes(view: View) {
            val intent = Intent(this, Changingactivity::class.java)
            startActivity(intent)
        }



}
