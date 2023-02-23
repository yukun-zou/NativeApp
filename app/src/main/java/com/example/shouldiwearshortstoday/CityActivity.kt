package com.example.shouldiwearshortstoday

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_page)
        val weather = Weather()
        val storage = Storage(this)
        val recycleView = findViewById<RecyclerView>(R.id.Recycle)
        val layoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                var cities = storage.cities
                val citylist =  mutableListOf<CityData>()
                var i =0;
                for(k in cities){
                    val cityname = k.key
                    val lat = k.value[0]
                    var lon = k.value[1]
                    val weatherResponse = weather.getWeatherAPI(lat.toString(), lon.toString())
                    val temperature = weatherResponse.getJSONObject("current_weather").getString("temperature").toString()
                    val condition = weatherResponse.getJSONObject("current_weather").getInt("weathercode")
                    i += 1;
                    citylist.add(CityData(i,cityname,wmoInterpreter(condition),temperature))
                }
                runOnUiThread {
                    recycleView.adapter = CityAdapter(citylist)
                }
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

    fun changeActivity(view: View){
        val deleteButton:ImageView = findViewById(R.id.deleteButton);
        val plusButton:ImageView = findViewById((R.id.plusbutton));
        val text:TextView = findViewById(R.id.changCity)
        val checkbox = findViewById<CheckBox>(R.id.myCheckBox)
        if(deleteButton.visibility == View.GONE){
            deleteButton.visibility =View.VISIBLE
            plusButton.visibility = View.VISIBLE
            text.visibility = View.GONE

        }
        else{
            deleteButton.visibility =View.GONE;
            plusButton.visibility = View.GONE;
            text.visibility = View.VISIBLE;
        }
    }


    fun closeSettingsActivity(view: View) {
        finish()
    }


    fun AddActivity(view: View){
//        println(getCityCoordinates("Stockholm"))
        val intent = Intent(this, CityAdd::class.java)
        startActivity(intent)
    }


}