package com.practice.groceryinkotlin.ui.grocery_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practice.groceryinkotlin.R
import com.practice.groceryinkotlin.data.GroceryItem

class GroceryRecyclerAdapter(private var items: ArrayList<GroceryItem>,
                             private val context: Context,
                             private val onClickListener: OnClickListener
)
    : RecyclerView.Adapter<GroceryRecyclerAdapter.GroceryViewHolder>() {

    interface OnClickListener {
        fun onClick(groceryItem: GroceryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        return GroceryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_grocery, parent, false), onClickListener
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        var groceryItem: GroceryItem = items[position]
        holder.setItem(groceryItem)
        holder.name.text = groceryItem.name
        holder.amount.text = groceryItem.amount
        holder.location.text = groceryItem.location
        holder.itemView.setOnClickListener { this }
        holder.checkBox.setOnClickListener {
            items.remove(groceryItem)
            notifyDataSetChanged()
        }
    }

    class GroceryViewHolder(itemView: View, val onClickListener: OnClickListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var mGroceryItem: GroceryItem
        var name: TextView = itemView.findViewById(R.id.item_name)
        var amount: TextView = itemView.findViewById(R.id.item_amount)
        var location: TextView = itemView.findViewById(R.id.item_location)
        var checkBox: CheckBox = itemView.findViewById(R.id.item_completed)

        fun setItem(groceryItem: GroceryItem) {
            mGroceryItem = groceryItem
        }

        override fun onClick(p0: View?) {
            onClickListener.onClick(mGroceryItem)
        }

    }
}