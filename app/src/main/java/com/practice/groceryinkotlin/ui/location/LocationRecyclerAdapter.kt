package com.practice.groceryinkotlin.ui.location

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practice.groceryinkotlin.R

class LocationRecyclerAdapter(private var locations: List<String>, private val context: Context,
                              private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<LocationRecyclerAdapter.LocationHolder>() {

    interface OnItemClickListener {
        fun onSelect(location: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        return LocationHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.list_item_location, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        var s = locations[position]
        holder.location.text = s
        holder.location.setOnClickListener {
            Log.i("TAG", holder.location.text.toString())
            onItemClickListener.onSelect(holder.location.text.toString())
        }
    }


    class LocationHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var location: TextView = itemView.findViewById(R.id.location)
    }
}