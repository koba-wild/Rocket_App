package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.*
import com.example.dragonx.models.Rocket
import com.example.dragonx.viewmodel.RocketListViewModel


class RocketListFragment : Fragment(), OnRocketClickListener {
    private val bundle = Bundle()
    private val fragmentDetails = RocketDetailsFragment()
    private lateinit var viewModel: RocketListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.fragment_rocket_list, container, false)
        viewModel = ViewModelProvider(this).get(RocketListViewModel::class.java)

        viewModel.parsedRockets.observe(viewLifecycleOwner, {
            var recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView?.layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            recyclerView?.addItemDecoration(topSpacingItemDecoration)
            recyclerView?.adapter = RocketRecyclerAdapter(it, this)
        })
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