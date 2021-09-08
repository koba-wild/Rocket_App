package com.example.dragonx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.JsonDetailsParser
import com.example.dragonx.R
import com.example.dragonx.models.Rocket
import kotlinx.android.synthetic.main.fragment_rocket_details.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RocketDetailsFragment : Fragment() {
    private val parser = JsonDetailsParser()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        var rocketNumber = arguments?.getInt("Rocket's number")
        CoroutineScope(Dispatchers.Default).launch {
            var(rocket, imageSlides) = parser.parseJson(rocketNumber,view)
            getRocketData(rocket as Rocket, imageSlides as ArrayList<SlideModel>, view)
        }
        return view
    }
    private suspend fun getRocketData(rocket: Rocket, listOfImages:ArrayList<SlideModel>, view: View){
        withContext(Dispatchers.Main) {
            view.rocketName.text = rocket.name
            view.description.text = rocket.description
            view.wikiLink.text = rocket.link
            view.heightRocket.text = rocket.height
            view.mass.text = rocket.mass
            view.year.text = rocket.year
            view.imageSlider.setImageList(listOfImages, ScaleTypes.FIT)
        }
    }
}