package com.example.shouldiwearshortstoday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter (
    private val context: Context, private val dataset: List<CityData>
    ):RecyclerView.Adapter<CityAdapter.ItemViewHolder>(){
    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val TitleText: TextView = view.findViewById(R.id.titleTextView)
        val ContentText:TextView = view.findViewById(R.id.descriptionTextView)

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
        val card = dataset[position]
        holder.TitleText.text = card.CityName
        holder.ContentText.text = card.WeatherCondition
    }

}