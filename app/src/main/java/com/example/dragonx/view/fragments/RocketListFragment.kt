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
import com.example.dragonx.viewmodel.RocketListViewModel
import kotlinx.coroutines.*


class RocketListFragment : Fragment(), OnRocketClickListener {
    private val fragmentDetails = RocketDetailsFragment()
    private lateinit var viewModel: RocketListViewModel
    private val rocketsData = MutableLiveData<List<Rocket>>()
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    private var _binding: FragmentRocketListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  =  FragmentRocketListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(RocketListViewModel::class.java)
        myScope.launch{
            rocketsData.postValue(viewModel.getRocketData())
        }

        rocketsData.observe(viewLifecycleOwner, {
            var recyclerView = binding.recyclerView
            recyclerView.layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            recyclerView.addItemDecoration(topSpacingItemDecoration)
            recyclerView.adapter = RocketRecyclerAdapter(it, this)
        })
        return view
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        val bundle = Bundle()
        bundle.putInt("Rocket's number", position)
        fragmentDetails.arguments = bundle
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_rocket_list, fragmentDetails)
        transaction?.commit()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}