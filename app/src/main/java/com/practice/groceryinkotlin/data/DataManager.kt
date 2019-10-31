package com.practice.groceryinkotlin.data

import android.content.Context
import android.util.Log
import kotlinx.coroutines.launch

const val TAG: String = "DataManager"

class DataManager(context: Context) {

    var mGroceryDao = GroceryDatabase.getInstance(context).mGroceryItemDao()

    suspend fun insert(groceryItem: GroceryItem) {
        try {
            launch {
                mGroceryDao.insert(groceryItem)
                Log.i(TAG, groceryItem.name+" was successfully inserted.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "unable to insert item: "+e.message)
        }
    }

    suspend fun delete(groceryItem: GroceryItem) {
        try {
            launch {
                mGroceryDao.delete(groceryItem)
                Log.i(TAG, groceryItem.name+" was successfully deleted.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "unable to delete item: "+e.message)
        }
    }

    companion object {

        @Volatile
        private lateinit var INSTANCE: DataManager

        fun getInstance(context: Context): DataManager {
            synchronized(this) {
                if (!::INSTANCE.isInitialized)
                    INSTANCE = DataManager(context)
                return INSTANCE
            }
        }
    }
}