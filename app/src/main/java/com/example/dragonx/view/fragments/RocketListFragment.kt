package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dragonx.*
import com.example.dragonx.databinding.FragmentRocketListBinding
import com.example.dragonx.models.Rocket
import com.example.dragonx.util.Constants.Companion.ROCKET_NUMBER
import com.example.dragonx.viewmodel.RocketListViewModel
import kotlinx.coroutines.*


class RocketListFragment : Fragment(), OnRocketClickListener {

    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    companion object {
            fun newInstance(): RocketListFragment {
                val fragment = RocketListFragment()
                return fragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  =  FragmentRocketListBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        val viewModel = ViewModelProvider(this).get(RocketListViewModel::class.java)
        viewModel.getRockets()

        viewModel.rocketsData.observe(viewLifecycleOwner, {
            recyclerView.adapter = RocketRecyclerAdapter(it, this)
        })
        return view
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_rocket_list, RocketDetailsFragment.newInstance(position))
        transaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}