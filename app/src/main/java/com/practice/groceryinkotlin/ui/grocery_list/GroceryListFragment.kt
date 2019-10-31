package com.practice.groceryinkotlin.ui.grocery_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.groceryinkotlin.R
import com.practice.groceryinkotlin.data.GroceryItem
import com.practice.groceryinkotlin.ui.GroceryItemFragment
import kotlinx.android.synthetic.main.fragment_grocery_list.*

class GroceryListFragment : Fragment(), GroceryRecyclerAdapter.OnClickListener {

    var mBundle: Bundle? = null
    lateinit var groceryListAdapter: GroceryRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grocery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initButtons()
    }

    private fun initData(): ArrayList<GroceryItem> {
        val list = ArrayList<GroceryItem>()
        (0..5).forEach {
            list.add(GroceryItem(it, "beans_"+it, "$1.50", "Publix", false, false))
        }
        return list
    }

    private fun initRecyclerView() {

        grocery_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            groceryListAdapter =
                GroceryRecyclerAdapter(
                    initData(),
                    context,
                    this@GroceryListFragment
                )
            adapter = groceryListAdapter
        }
    }

    private fun initButtons() {
        create_new.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.list_fragment_container, GroceryItemFragment()).commit()
        }

        create_recurring.setOnClickListener {

        }
    }

    override fun onClick(groceryItem: GroceryItem) {
        Log.i("LIST", "LIST")
        mBundle?.putString(GroceryItemFragment.ITEM_UPDATING, "")
        mBundle?.putString(BUNDLE_NAME, groceryItem.name)
        mBundle?.putString(BUNDLE_AMOUNT, groceryItem.amount)
        mBundle?.putBoolean(BUNDLE_RECURRING, groceryItem.recurring)
        var frag = GroceryItemFragment()
        frag.arguments = mBundle
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment_container, frag).commit()
    }

    companion object {
        const val BUNDLE_NAME: String = "BUNDLE_NAME"
        const val BUNDLE_AMOUNT: String = "BUNDLE_AMOUNT"
        const val BUNDLE_RECURRING: String = "BUNDLE_RECURRING"
    }
}