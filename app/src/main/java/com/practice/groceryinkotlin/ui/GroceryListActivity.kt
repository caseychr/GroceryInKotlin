package com.practice.groceryinkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.groceryinkotlin.R
import com.practice.groceryinkotlin.ui.grocery_list.GroceryListFragment

class GroceryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery_list)

        supportFragmentManager.beginTransaction()
            .add(R.id.list_fragment_container, GroceryListFragment()).commit()
    }
}
