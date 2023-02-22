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
    private var mVelocityTracker: VelocityTracker? = null
    private lateinit var storage : Storage
    private lateinit var layout: RelativeLayout
    private  lateinit var weather: Weather
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage = Storage(this)
        weather = Weather()
        initValues()
        getWeather()
        val slider = findViewById<RangeSlider>(R.id.slider)
        slider.addOnChangeListener(RangeSlider.OnChangeListener { slider, value, fromUser ->
            getWeather()
        })
        layout = findViewById(R.id.relativeLayout)
        layout.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                storage.swipeLeft()
                getWeather()
                updateNavbar()
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                storage.swipeRight()
                getWeather()
                updateNavbar()
            }
            override fun onSwipeUp() {
                super.onSwipeUp()

            }
            override fun onSwipeDown() {
                super.onSwipeDown()

            }
        })
        getCurrentWeather()
    }

    fun getCurrentWeather(){
        val weatherCoroutine = lifecycleScope.async {
            val coord = storage.cities.get(storage.currentCity)
            val lat = coord!![0]
            val long = coord!![1]
            weather.getCurrentWeather(lat, long)
        }
        weatherCoroutine.invokeOnCompletion {
            val c =weatherCoroutine.getCompleted()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWeather() {
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

    fun initValues() {
        storage.getValuesFromStorage()
        val slider = findViewById<RangeSlider>(R.id.slider)
        slider.values = listOf(storage.defaultStart.toFloat(), storage.defaultEnd.toFloat())
        findViewById<TextView>(R.id.currentCity).text = storage.currentCity
        updateNavbar()
        updateClothing()
    }

    fun updateClothing() {
        findViewById<ImageView>(R.id.hat).setImageResource(storage.hat)
        findViewById<ImageView>(R.id.scarf).setImageResource(storage.scarf)
        findViewById<ImageView>(R.id.tshirt).setImageResource(storage.tshirt)
        findViewById<ImageView>(R.id.hoodie).setImageResource(storage.hoodie)
        findViewById<ImageView>(R.id.winterJacket).setImageResource(storage.winterJacket)
        findViewById<ImageView>(R.id.shorts).setImageResource(storage.shorts)
        findViewById<ImageView>(R.id.trousers).setImageResource(storage.trousers)
        findViewById<ImageView>(R.id.umbrella).setImageResource(storage.umbrella)
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

    fun updateNavbar() {
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
        findViewById<TextView>(R.id.currentCity).text = storage.currentCity
    }

    fun openChangingclothes(view: View) {
        val intent = Intent(this, Changingactivity::class.java)
        startActivity(intent)
    }

    fun openCityActivity(view: View) {
        val intent = Intent(this, CityActivity::class.java)
        startActivity(intent)
    }


}
