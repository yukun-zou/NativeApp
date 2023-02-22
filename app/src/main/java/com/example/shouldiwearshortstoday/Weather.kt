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

    suspend fun clothingAlgorithm(start: Int, end: Int, lat:Int, long: Int, storage: Storage): Array<String> {

//        val locationResponse = getLocationAPI()
//        val lat = locationResponse.getJSONArray("results").getJSONObject(0).getString("latitude")
//        val long = locationResponse.getJSONArray("results").getJSONObject(0).getString("longitude")
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
        if (minTemp < storage.freezing) {
            clothingType = "freezing"
        } else if (minTemp < storage.cold) {
            clothingType = "cold"
        } else if (minTemp < storage.comfortable) {
            clothingType = "comfortable"
        } else if (minTemp < storage.warm) {
            clothingType = "warm"
        } else {
            clothingType = "hot"
        }
        var umbrella: Boolean = (maxPre > 0)
        val array = Array(2) { "" }
        array[0] = clothingType
        array[1] = umbrella.toString()
        return array
    }
    suspend fun getCurrentWeather(lat: Int, long: Int): Array<String>{
        val weatherResponse = getWeatherAPI(lat.toString(), long.toString())
        val temp = weatherResponse.getJSONObject("current_weather").getString("temperature")
        val weatherCode = weatherResponse.getJSONObject("current_weather").getInt("weathercode")
        val weatherCondition = wmoInterpreter(weatherCode)
        return arrayOf(temp, weatherCondition)
    }
}
