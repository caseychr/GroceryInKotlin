package com.practice.groceryinkotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_item")
data class GroceryItem (var name: String?, var amount: String?, var location: String?,
                       var completed: Boolean, var recurring: Boolean) {

    constructor(id: Int, name: String?, amount: String?, location: String?,
                completed: Boolean, recurring: Boolean) : this(name, amount, location, completed, recurring) {
        @PrimaryKey(autoGenerate = true)
        id
    }


    override fun toString(): String {
        return "GroceryItem(id=$id, name='$name', amount='$amount', location='$location', completed=$completed, recurring=$recurring)"
    }
}