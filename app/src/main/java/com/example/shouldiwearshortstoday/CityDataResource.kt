package com.example.shouldiwearshortstoday

class CityDataResource {
    fun GetData():List<CityData>{
        return listOf<CityData>(
            CityData("Stockholm","Sunny",1),
            CityData("London","Sunny",1),
            CityData("NewYork","Sunny",1),
        )
    }
}