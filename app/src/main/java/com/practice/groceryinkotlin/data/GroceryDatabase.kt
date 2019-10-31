package com.practice.groceryinkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItem::class], version = 1, exportSchema = false)
abstract class GroceryDatabase : RoomDatabase() {

    abstract fun mGroceryItemDao(): GroceryDao

    companion object {
        const val DATABASE_NAME: String = "grocery_db"

        @Volatile
        private lateinit var INSTANCE: GroceryDatabase

        fun getInstance(context: Context): GroceryDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized)
                    INSTANCE = Room.databaseBuilder(context, GroceryDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration() // In a state of migration, re-construct DB. Loose data.
                        .build()
                return INSTANCE
            }
        }
    }
}