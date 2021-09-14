package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.dragonx.databinding.FragmentRocketDetailsBinding
import com.example.dragonx.models.Rocket
import com.example.dragonx.viewmodel.RocketDetailsViewModel
import kotlinx.coroutines.*

class RocketDetailsFragment : Fragment() {
    private lateinit var viewModel: RocketDetailsViewModel
    private val rocket = MutableLiveData<Rocket>()
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    private var _binding: FragmentRocketDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRocketDetailsBinding.inflate(inflater, container, false)
        var rocketNumber = arguments?.getInt("Rocket's number")
        viewModel = ViewModelProvider(this).get(RocketDetailsViewModel::class.java)

        myScope.launch {
            rocket.postValue(viewModel.parseJson(rocketNumber))
        }

        rocket.observe(viewLifecycleOwner, {
            binding.rocketName.text = it.name
            binding.description.text = it.description
            binding.wikiLink.text = it.wikipedia
            binding.heightRocket.text = it.height
            binding.mass.text = it.mass
            binding.year.text = it.first_flight
            binding.imageSlider.setImageList(viewModel.imageList, ScaleTypes.FIT)
        })
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}