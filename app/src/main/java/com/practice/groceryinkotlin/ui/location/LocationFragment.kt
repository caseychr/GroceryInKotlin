package com.practice.groceryinkotlin.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.groceryinkotlin.R
import com.practice.groceryinkotlin.ui.GroceryItemFragment
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : Fragment(), LocationRecyclerAdapter.OnItemClickListener {

    var mBundle: Bundle? = null

    lateinit var locationAdapter: LocationRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        location_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            locationAdapter =
                LocationRecyclerAdapter(
                    initData(),
                    context,
                    this@LocationFragment
                )
            adapter = locationAdapter
        }
    }

    private fun initData(): List<String> {
        val list = mutableListOf<String>()
        list.add("Publix")
        list.add("Kroger")
        list.add("Trader Joe's")
        list.add("ALDI")
        list.add("Walmart")
        list.sort()
        return list
    }

    override fun onSelect(location: String) {
        val frag = GroceryItemFragment()
        mBundle = Bundle()
        mBundle?.putString(GroceryItemFragment.ITEM_LOCATION_BUNDLE, location)
        frag.arguments = mBundle
        activity!!.supportFragmentManager.beginTransaction().add(R.id.list_fragment_container, frag).commit()

    }

}
