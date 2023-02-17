package com.example.shouldiwearshortstoday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class Weather : ViewModel(){
    suspend fun getWeatherAPI(lat: String, long: String) {

        return withContext(Dispatchers.IO) {

        }
    }
    fun clothingAlgorithm(){
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherAPI("60", "18")
        }
    }
}