package com.example.shouldiwearshortstoday

import android.content.Context
import android.content.SharedPreferences

class Storage(context: Context) {
    private val APP_PREF_VALUES= "com.example.shouldiwearshortstoday.values"
    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREF_VALUES,Context.MODE_PRIVATE)
    var currentCity: String = "Stockholm"
    var defaultStart: Int = 8
    var defaultEnd: Int = 18
    var freezing: Int = 0
    var cold: Int = 10
    var comfortable: Int = 15
    var warm: Int = 23
    var hat: String = "@drawable/hat"
    var scarf: String = "@drawable/scarf"
    var hoodie: String = "@drawable/hoodie"
    var tshirt: String = "@drawable/tshirt"
    var winterJacket: String = "@drawable/winter_jacket"
    var trousers: String = "@drawable/trousers"
    var shorts: String = "@drawable/shorts"
    var cities = mutableMapOf<String, Array<Int>>("Stockholm" to arrayOf(60, 18))
    fun getValuesFromStorage() {
        currentCity = preferences.getString("currentCity", "Stockholm").toString()
        defaultStart = preferences.getInt("defaultStart", 8)
        defaultEnd = preferences.getInt("defaultEnd", 18)
        freezing = preferences.getInt("freezing", 0)
        cold = preferences.getInt("cold", 10)
        comfortable = preferences.getInt("comfortable", 15)
        warm = preferences.getInt("warm", 23)
        hat = preferences.getString("hat", "@drawable/hat").toString()
        scarf = preferences.getString("scarf", "@drawable/scarf").toString()
        hoodie = preferences.getString("hoodie", "@drawable/hoodie").toString()
        tshirt = preferences.getString("tshirt", "@drawable/tshirt").toString()
        winterJacket = preferences.getString("winterJacket", "@drawable/winterJacket").toString()
        trousers = preferences.getString("trousers", "@drawable/trousers").toString()
        shorts = preferences.getString("shorts", "@drawable/shorts").toString()
        stringToCities(preferences.getString("cities", "Stockholm,60,18").toString())
    }
    fun stringToCities(string: String){
        val listOfCities = string.split(";")
        var cMap = mutableMapOf<String, Array<Int>>()
        for (s in listOfCities){
            val i = s.split(",")
            cMap[i[0]] = arrayOf(i[1].toInt(), i[2].toInt())
        }
        cities = cMap
    }
    fun citiesToString():String{
        var cityString = ""
        for(k in cities){
            val values = k.value
            cityString += k.key
            cityString += ","
            cityString += values[0].toString()
            cityString += ","
            cityString += values[1].toString()
            cityString += ";"
        }
        return cityString.dropLast(1)
    }
    fun setDefaultStartEnd(start: Int, end: Int){
        val edit = preferences.edit()
        edit.putInt("defaultStart", start)
        edit.putInt("defaultEnd", end)
        edit.apply()
    }
    fun addCity(City: String, lat: Int, long: Int){
        cities.put(City, arrayOf(lat, long))
        val citiesString = citiesToString()
        val edit = preferences.edit()
        edit.putString("cities", citiesString)
        edit.apply()
    }
    fun swipeLeft(){
        var currentCityIndex = cities.keys.indexOf(currentCity)
        currentCityIndex = (currentCityIndex - 1 + cities.size)%cities.size
        currentCity = cities.keys.elementAt(currentCityIndex)
        val edit = preferences.edit()
        edit.putString("currentCity", currentCity)
        edit.apply()
    }
    fun swipeRight(){
        var currentCityIndex = cities.keys.indexOf(currentCity)
        currentCityIndex = (currentCityIndex + 1 + cities.size)%cities.size
        currentCity = cities.keys.elementAt(currentCityIndex)
        val edit = preferences.edit()
        edit.putString("currentCity", currentCity)
        edit.apply()
    }
}