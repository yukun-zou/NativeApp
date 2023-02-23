package com.example.shouldiwearshortstoday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter (private val dataset: List<CityData>
    ):RecyclerView.Adapter<CityAdapter.ItemViewHolder>(){
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val TitleText: TextView = view.findViewById(R.id.titleTextView)
        val ContentText:TextView = view.findViewById(R.id.descriptionTextView)
        val TempText:TextView = view.findViewById(R.id.tempTextView)
        val button:Button = view.findViewById(R.id.button3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.citycard, parent, false)
        return ItemViewHolder(adapterLayout)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = dataset[position]
        holder.TitleText.text = data.CityName
        holder.ContentText.text = data.WeatherCondition
        holder.TempText.text = data.Temperature
        holder.button.visibility = View.VISIBLE
    }

    fun toggle(holder: ItemViewHolder){
        holder.button.visibility = View.INVISIBLE
    }

}