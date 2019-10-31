package com.practice.groceryinkotlin.data

import androidx.room.*

@Dao
interface GroceryDao {

    @Insert
    suspend fun insert(groceryItem: GroceryItem)

    @Update
    fun update(groceryItem: GroceryItem)

    @Delete
    suspend fun delete(groceryItem: GroceryItem)

    @Query("SELECT * FROM grocery_item WHERE completed = :isCompleted ORDER BY location")
    fun getAllNonCompletedItems(isCompleted: Boolean): List<GroceryItem>

    @Query("SELECT * FROM grocery_item WHERE recurring = :isRecurring AND completed = :isCompleted")
    fun getRecurringItems(isRecurring: Boolean, isCompleted: Boolean): List<GroceryItem>



}