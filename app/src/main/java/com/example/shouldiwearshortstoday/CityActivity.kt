package com.example.shouldiwearshortstoday

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CityActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_page)
        val mydata = CityDataResource().GetData()
        val recycleView = findViewById<RecyclerView>(R.id.Recycle)
        val layoutManager = LinearLayoutManager(this)
        recycleView.layoutManager = layoutManager
        recycleView.adapter = CityAdapter(this,mydata)
    }

    fun changeActivity(view: View){
        val deleteButton:ImageView = findViewById(R.id.deleteButton);
        val plusButton:ImageView = findViewById((R.id.plusbutton));
        val text:TextView = findViewById(R.id.changCity)
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
}