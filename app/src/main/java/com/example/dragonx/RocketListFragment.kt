package com.example.dragonx

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

class RocketListFragment : Fragment(),OnRocketClickListener {
    val bundle = Bundle()
    val parser = JsonParser()
    var rockets = arrayListOf<Rocket>()
    val fragmentDetails = RocketDetailsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.fragment_rocket_list, container, false)
        CoroutineScope(Default).launch {
            rockets = parser.getRocketData()
        }
        var recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        recyclerView?.addItemDecoration(topSpacingItemDecoration)
        recyclerView?.adapter = RocketRecyclerAdapter(rockets, this)
        return view
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        bundle.putInt("Rocket's number", position)
        fragmentDetails.arguments = bundle
        getFragmentManager()?.beginTransaction()
            ?.replace(R.id.fragment_rocket_list, fragmentDetails)
            ?.commit()
    }
}