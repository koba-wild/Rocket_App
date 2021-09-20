package com.example.dragonx.presentation.RocketDetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.dragonx.R
import kotlinx.android.synthetic.main.fragment_rocket_details.view.*
import com.example.dragonx.models.Rocket
import com.example.dragonx.util.Constants.Companion.ROCKET_NUMBER
import com.example.dragonx.viewmodel.ViewModelFactory

class RocketDetailsFragment : Fragment() {

    companion object {
        private const val TAG = "KobaLOG"
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
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        Log.d(TAG, "onCreateView RocketDetailsFragment")
        var rocketNumber = arguments?.getInt(ROCKET_NUMBER)
        val viewModelFactory = ViewModelFactory(rocketNumber)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        viewModel.rocketDetails.observe(viewLifecycleOwner, {it:Rocket ->
            view.rocketName.text = it.name
            view.description.text = it.description
            view.wikiLink.text = it.wikipedia
            view.heightRocket.text = it.height
            view.mass.text = it.mass
            view.year.text = it.first_flight
            view.imageSlider.setImageList(it.imageList, ScaleTypes.FIT)
        })
        return view
    }



    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart RocketDetailsFragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume RocketDetailsFragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause RocketDetailsFragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop RocketDetailsFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy RocketDetailsFragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach RocketDetailsFragment")
    }


}