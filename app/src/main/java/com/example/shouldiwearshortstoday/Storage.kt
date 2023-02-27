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
    var hat: Int = R.drawable.hat
    var scarf: Int = R.drawable.scarf
    var hoodie: Int = R.drawable.hoodie
    var tshirt: Int = R.drawable.tshirt
    var winterJacket: Int = R.drawable.winter_jacket
    var trousers: Int = R.drawable.trousers
    var shorts: Int = R.drawable.shorts
    var umbrella: Int = R.drawable.umbrella
    var cities = mutableMapOf<String, Array<Int>>("Stockholm" to arrayOf(60, 18))
    fun getValuesFromStorage() {
        currentCity = preferences.getString("currentCity", "Stockholm").toString()
        defaultStart = preferences.getInt("defaultStart", 8)
        defaultEnd = preferences.getInt("defaultEnd", 18)
        freezing = preferences.getInt("freezing", 0)
        cold = preferences.getInt("cold", 10)
        comfortable = preferences.getInt("comfortable", 15)
        warm = preferences.getInt("warm", 23)
        hat = preferences.getInt("hat", R.drawable.hat)
        scarf = preferences.getInt("scarf", R.drawable.scarf)
        hoodie = preferences.getInt("hoodie", R.drawable.hoodie)
        tshirt = preferences.getInt("tshirt", R.drawable.tshirt)
        winterJacket = preferences.getInt("winterJacket", R.drawable.winter_jacket)
        trousers = preferences.getInt("trousers", R.drawable.trousers)
        shorts = preferences.getInt("shorts", R.drawable.shorts)
        umbrella = preferences.getInt("shorts", R.drawable.umbrella)
        stringToCities(preferences.getString("cities", "Stockholm,60,18").toString())
    }
    fun setTempType(){
        val edit = preferences.edit()
        edit.putInt("freezing", freezing)
        edit.putInt("cold", cold)
        edit.putInt("comfortable", comfortable)
        edit.putInt("warm", warm)
        edit.apply()
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
    fun addCity(city: String, lat: Int, long: Int){
        cities.put(city, arrayOf(lat, long))
        val citiesString = citiesToString()
        val edit = preferences.edit()
        edit.putString("cities", citiesString)
        edit.apply()
    }
    fun deleteCity(city: String){
        cities.remove(city)
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
    fun changeHat(src: Int){
        hat = src
        val edit = preferences.edit()
        edit.putInt("hat", hat)
        edit.apply()
    }
    fun changeScarf(src: Int){
        scarf = src
        val edit = preferences.edit()
        edit.putInt("scarf", scarf)
        edit.apply()
    }
    fun changeUmbrella(src: Int){
        umbrella = src
        val edit = preferences.edit()
        edit.putInt("umbrella", umbrella)
        edit.apply()
    }
    fun changeTshirt(src: Int){
        tshirt = src
        val edit = preferences.edit()
        edit.putInt("tshirt", tshirt)
        edit.apply()
    }
    fun changeHoodie(src: Int){
        hoodie = src
        val edit = preferences.edit()
        edit.putInt("hoodie", hoodie)
        edit.apply()
    }
    fun changeWinterJacket(src: Int){
        winterJacket = src
        val edit = preferences.edit()
        edit.putInt("winterJacket", winterJacket)
        edit.apply()
    }
    fun changeTrousers(src: Int){
        trousers = src
        val edit = preferences.edit()
        edit.putInt("trousers", trousers)
        edit.apply()
    }
    fun changeShorts(src: Int){
        shorts = src
        val edit = preferences.edit()
        edit.putInt("shorts", shorts)
        edit.apply()
    }
}
