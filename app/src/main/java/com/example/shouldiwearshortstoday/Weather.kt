package com.example.shouldiwearshortstoday

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Weather{
    private suspend fun getWeatherAPI(lat: String, long: String): JSONObject {
        return withContext(Dispatchers.IO) {
            var address =
                "https://api.open-meteo.com/v1/forecast?latitude=€LAT&longitude=€LONG&hourly=apparent_temperature,precipitation&current_weather=true"
            address = address.replace("€LAT", lat)
            address = address.replace("€LONG", long)
            val connection = URL(address).openConnection() as HttpURLConnection
            var text: String
            try {
                connection.connect();
                text =
                    connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return@withContext JSONObject(text)
        }
    }

    private suspend fun getLocationAPI(city: String): JSONObject {
        return withContext(Dispatchers.IO) {
            var address = "https://geocoding-api.open-meteo.com/v1/search?name=€NAME"
            address = address.replace("€NAME", city)
            val connection = URL(address).openConnection() as HttpURLConnection
            var text: String
            try {
                connection.connect();
                text =
                    connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return@withContext JSONObject(text)
        }
    }

    suspend fun clothingAlgorithm(start: Int, end: Int): Array<String> {

        val locationResponse = getLocationAPI("Stockholm")
        val lat = locationResponse.getJSONArray("results").getJSONObject(0).getString("latitude")
        val long = locationResponse.getJSONArray("results").getJSONObject(0).getString("longitude")
        val weatherResponse = getWeatherAPI(lat.toString(), long.toString())
        val temperature =
            weatherResponse.getJSONObject("hourly").getJSONArray("apparent_temperature")
        val precipitation = weatherResponse.getJSONObject("hourly").getJSONArray("precipitation")
        var minTemp = temperature.getInt(start)
        var maxPre = precipitation.getInt(start)
        for (i in start..end) {
            if (temperature.getInt(i) < minTemp) {
                minTemp = temperature.getInt(i)
            }
            if (precipitation.getInt(i) > maxPre) {
                maxPre = precipitation.getInt(i)
            }
        }
        var clothingType: String
        var umbrella: Boolean
        if (minTemp < 0) {
            clothingType = "freezing"
        } else if (minTemp < 10) {
            clothingType = "cold"
        } else if (minTemp < 15) {
            clothingType = "comfortable"
        } else if (minTemp < 23) {
            clothingType = "warm"
        } else {
            clothingType = "hot"
        }
        umbrella = (maxPre > 0)
        val array = Array(2) { "" }
        array[0] = clothingType
        array[1] = umbrella.toString()
        return array
    }
}
