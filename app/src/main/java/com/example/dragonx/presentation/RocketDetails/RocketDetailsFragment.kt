package com.example.dragonx.presentation.RocketDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.dragonx.R
import com.example.dragonx.presentation.RocketList.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_rocket_details.view.*
import com.example.dragonx.model.util.Constants.Companion.ROCKET_NUMBER
import com.example.dragonx.model.data.RocketDetails


class RocketDetailsFragment : Fragment() {
    private val args: RocketDetailsFragmentArgs by navArgs()

//    companion object {
//        private const val TAG = "RocketDetails Logs"
//            fun newInstance(position: Int): RocketDetailsFragment {
//                val fragmentDetails = RocketDetailsFragment()
//                val bundle = Bundle()
//                bundle.putInt(ROCKET_NUMBER, position)
//                fragmentDetails.arguments = bundle
//                return fragmentDetails
//            }
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        val rocketNumber = args.rocketNumber
        val viewModelFactory = ViewModelFactory(rocketNumber)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        viewModel.rocketDetails.observe(viewLifecycleOwner, {it: RocketDetails ->
            view.rocketName.text = it.name
            view.description.text = it.description
            view.wikiLink.text = it.wikipedia
            view.heightRocket.text = getString(R.string.rocket_diameter, it.heightDiameter, it.heightFeet )
            view.mass.text = getString(R.string.rocket_mass, it.massKg, it.massLb)
            view.year.text = it.firstFlight
            view.imageSlider.setImageList(it.flickrImages, ScaleTypes.FIT)
})
        return view
    }
}