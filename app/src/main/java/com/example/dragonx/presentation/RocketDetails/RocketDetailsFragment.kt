package com.example.dragonx.presentation.RocketDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.dragonx.R
import com.example.dragonx.presentation.RocketList.ViewModelFactory
import com.example.dragonx.model.data.RocketDetails


class RocketDetailsFragment : Fragment() {
    private val args: RocketDetailsFragmentArgs by navArgs()
    private val rocketName: TextView by lazy { requireView().findViewById(R.id.rocketName) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        val imageSlider = view.findViewById<ViewPager2>(R.id.viewPager)
//        val rocketName = view.findViewById<TextView>(R.id.rocketName)
        val description = view.findViewById<TextView>(R.id.description)
        val wikiLink = view.findViewById<TextView>(R.id.wikiLink)
        val heightRocket = view.findViewById<TextView>(R.id.heightRocket)
        val mass = view.findViewById<TextView>(R.id.mass)
        val year = view.findViewById<TextView>(R.id.year)
        val rocketNumber = args.rocketNumber
        val viewModelFactory = ViewModelFactory(rocketNumber)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        val adapter = ImageSliderAdapter()
        imageSlider.adapter = adapter
        viewModel.rocketDetails.observe(viewLifecycleOwner, {it: RocketDetails ->
            rocketName.text = it.name
            description.text = it.description
            wikiLink.text = it.wikipedia
            heightRocket.text = getString(R.string.rocket_diameter, it.heightDiameter, it.heightFeet )
            mass.text = getString(R.string.rocket_mass, it.massKg, it.massLb)
            year.text = it.firstFlight
            adapter.submitList(it.flickrImages)

        })
        return view
    }
}