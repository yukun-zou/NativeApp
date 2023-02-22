package com.example.shouldiwearshortstoday

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private var mVelocityTracker: VelocityTracker? = null
    private lateinit var storage : Storage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage = Storage(this)
        val weather = Weather()
        initValues()
        getWeather(weather)
        val slider = findViewById<RangeSlider>(R.id.slider)
        slider.addOnChangeListener(RangeSlider.OnChangeListener { slider, value, fromUser ->
            getWeather(
                weather
            )
        })

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getWeather(weather: Weather) {
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
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                // Reset the velocity tracker back to its initial state.
                mVelocityTracker?.clear()
                // If necessary retrieve a new VelocityTracker object to watch the
                // velocity of a motion.
                mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                // Add a user's movement to the tracker.
                mVelocityTracker?.addMovement(event)
            }
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker?.apply {
                    val pointerId: Int = event.getPointerId(event.actionIndex)
                    addMovement(event)
                    // When you want to determine the velocity, call
                    // computeCurrentVelocity(). Then call getXVelocity()
                    // and getYVelocity() to retrieve the velocity for each pointer ID.
                    computeCurrentVelocity(10)
                    // Log velocity of pixels per second
                    // Best practice to use VelocityTrackerCompat where possible.
                    val xVel = getXVelocity(pointerId)
                    val yVel = getYVelocity(pointerId)
                    if(abs(xVel)> abs(yVel) && abs(xVel)> 10 && abs(yVel) > 10){
                        if(xVel> 0){
                            storage.swipeRight()
                            updateNavbar()
                        }
                        else{
                            storage.swipeLeft()
                            updateNavbar()
                        }
                    }
                    else{
                        if(yVel> 0){

                        }
                        else{

                        }
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker?.recycle()
                mVelocityTracker = null
            }
        }
        return true
    }
}
