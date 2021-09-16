package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.dragonx.databinding.FragmentRocketDetailsBinding
import com.example.dragonx.models.Rocket
import com.example.dragonx.util.Constants
import com.example.dragonx.util.Constants.Companion.ROCKET_NUMBER
import com.example.dragonx.viewmodel.RocketDetailsViewModel
import com.example.dragonx.viewmodel.ViewModelFactory

class RocketDetailsFragment : Fragment() {
    private var _binding: FragmentRocketDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
            fun newInstance(position: Int): RocketDetailsFragment {
                val fragmentDetails = RocketDetailsFragment()
                val bundle = Bundle()
                bundle.putInt(ROCKET_NUMBER, position)
                fragmentDetails.arguments = bundle
                return fragmentDetails
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRocketDetailsBinding.inflate(inflater, container, false)
        var rocketNumber = arguments?.getInt(ROCKET_NUMBER)
        val viewModelFactory = ViewModelFactory(rocketNumber)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        viewModel.getRockets()
        viewModel.rocketDetails.observe(viewLifecycleOwner, {
            binding.rocketName.text = it.name
            binding.description.text = it.description
            binding.wikiLink.text = it.wikipedia
            binding.heightRocket.text = it.height
            binding.mass.text = it.mass
            binding.year.text = it.first_flight
            binding.imageSlider.setImageList(it.imageList, ScaleTypes.FIT)
        })
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}