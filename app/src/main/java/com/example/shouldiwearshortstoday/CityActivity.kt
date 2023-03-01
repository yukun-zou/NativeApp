package com.example.shouldiwearshortstoday

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


class CityActivity: AppCompatActivity() {
    val citylist =  mutableListOf<CityData>()
    private lateinit var adapter: CityAdapter
    private var shownNoInternet: Boolean = false
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_page)
        var weather = Weather()
        var storage = Storage(this)
        storage.getValuesFromStorage()
        if(isNetworkConnected()) {
            val recycleView = findViewById<RecyclerView>(R.id.Recycle)
            var layoutManager = LinearLayoutManager(this)
            recycleView.layoutManager = layoutManager
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    storage.getValuesFromStorage()
                    var cities = storage.cities
                    println(cities)
                    var citylist = mutableListOf<CityData>()
                    var i = 0;
                    for (k in cities) {
                        val cityname = k.key
                        val lat = k.value[0]
                        var lon = k.value[1]
                        val weatherResponse = weather.getCurrentWeather(lat, lon)
                        i += 1;
                        citylist.add(CityData(i, cityname, weatherResponse[1], weatherResponse[0]))
                    }
                    runOnUiThread {
                        adapter = CityAdapter(citylist)
                        recycleView.adapter = adapter
                    }
                } catch (e: Exception) {
                    Log.e("OpenWeatherActivity", "Error retrieving weather data", e)
                }
            }
            recycleView.setOnTouchListener(object : OnSwipeTouchListener(this@CityActivity) {
                override fun onLongClick() {
                    super.onSwipeDown()
                    changeActivity(recycleView)
                }
            })
        }
        else {
            showNetDialog()
        }

    }
    fun showNetDialog(){
        if(!shownNoInternet) {
            shownNoInternet = true
            val builder = AlertDialog.Builder(this)
            builder.setTitle("No Internet Connection")
            builder.setMessage("Can't retrieve weather data")
            builder.setPositiveButton("OK") { dialog, which ->
                shownNoInternet = false
            }
            val dialog = builder.create()
            dialog.show()
        }
    }
    private fun isNetworkConnected(): Boolean {
        val connManager = this.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
        return networkCapabilities != null
    }

    fun changeActivity(view: View){
        if(isNetworkConnected()) {
            val plusButton: ImageView = findViewById((R.id.plusbutton));
            val text: TextView = findViewById(R.id.changCity)
            adapter.toggle()
            if (plusButton.visibility == View.GONE) {
                plusButton.visibility = View.VISIBLE
                text.visibility = View.GONE

            } else {
                plusButton.visibility = View.GONE;
                text.visibility = View.VISIBLE;
            }
        }
        else{
            showNetDialog()
        }
    }


    fun closeSettingsActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun addActivity(view: View){
//        println(getCityCoordinates("Stockholm"))
        val intent = Intent(this, CityAdd::class.java)
        startActivity(intent)
    }


}