package com.practice.groceryinkotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practice.groceryinkotlin.R
import com.practice.groceryinkotlin.data.DataManager
import com.practice.groceryinkotlin.data.GroceryItem
import com.practice.groceryinkotlin.ui.grocery_list.GroceryListFragment
import com.practice.groceryinkotlin.ui.location.LocationFragment
import kotlinx.android.synthetic.main.fragment_grocery_item.*
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.launch

class GroceryItemFragment : Fragment() {


    var mBundle: Bundle? = null
    lateinit var mGroceryItem: GroceryItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grocery_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidgets()
    }

    override fun onResume() {
        super.onResume()
        if(arguments != null && arguments!!.containsKey(GroceryItemFragment.ITEM_UPDATING)) {
            grocery_name.setText(arguments?.getString(GroceryListFragment.BUNDLE_NAME))
            grocery_amount.setText(arguments?.getString(GroceryListFragment.BUNDLE_AMOUNT))
            grocery_recurring.isChecked = arguments!!.getBoolean(GroceryListFragment.BUNDLE_RECURRING)
        }
    }

    fun initWidgets() {
        grocery_location.text = arguments?.getString(ITEM_LOCATION_BUNDLE)
        grocery_location.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().add(R.id.list_fragment_container,
                LocationFragment()
            ).commit()
        }
        add_new.setOnClickListener {
            launch(UI) {
                Log.i("GGG", "GGG")
                DataManager.getInstance(activity!!.baseContext).insert(mockGrocery())
                Log.i("GGG2", "GGG2")
            }
            activity!!.supportFragmentManager.beginTransaction().add(R.id.list_fragment_container,
                GroceryListFragment()
            ).commit()
        }
    }

    private fun mockGrocery(): GroceryItem {
        mGroceryItem = GroceryItem("beans", "$1.00", "Publix", false, false)
        return mGroceryItem
    }

    companion object {
        const val ITEM_LOCATION_BUNDLE: String = "ITEM_LOCATION_BUNDLE"
        const val ITEM_UPDATING: String = "ITEM_UPDATING"
    }
}
