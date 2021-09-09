package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.R
import com.example.dragonx.models.Rocket
import com.example.dragonx.viewmodel.RocketDetailsViewModel
import com.example.dragonx.viewmodel.RocketListViewModel
import kotlinx.android.synthetic.main.fragment_rocket_details.view.*
import kotlinx.coroutines.*

class RocketDetailsFragment : Fragment() {
    private lateinit var viewModel: RocketDetailsViewModel
    private val rocket = MutableLiveData<Rocket>()
    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        var rocketNumber = arguments?.getInt("Rocket's number")
        viewModel = ViewModelProvider(this).get(RocketDetailsViewModel::class.java)

        myScope.launch {
            rocket.postValue(viewModel.parseJson(rocketNumber))
        }

        rocket.observe(viewLifecycleOwner, {
            view.rocketName.text = it.name
            view.description.text = it.description
            view.wikiLink.text = it.link
            view.heightRocket.text = it.height
            view.mass.text = it.mass
            view.year.text = it.year
            view.imageSlider.setImageList(viewModel.imagesForSlider(rocketNumber), ScaleTypes.FIT)
        })
        return view
    }


}