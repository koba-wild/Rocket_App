package com.example.dragonx.presentation.RocketDetails

import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.NetworkService.NetworkService
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.util.RocketModel

class RocketDetailsParser {
    fun parseJson (rocketNumber:Int?): RocketModel {
        val rocketsList: List<Rocket> = NetworkService.getInstance()
            .buildApiService()
            .getRockets()
        val rocket = RocketModel()
        rocket.name = rocketsList[rocketNumber!!].name
        rocket.description = rocketsList[rocketNumber].description
        rocket.wikipedia = rocketsList[rocketNumber].wikipedia
        rocket.heightDiameter =
            rocketsList[rocketNumber].diameter.meters
        rocket.heightFeet = rocketsList[rocketNumber].diameter.feet
        rocket.massKg =
            rocketsList[rocketNumber].massKg
        rocket.massLb = rocketsList[rocketNumber].massLb
        rocket.firstFlight = rocketsList[rocketNumber].firstFlight
        val flickrImages = rocketsList[rocketNumber].flickrImages as ArrayList<String>
        val imgArray = arrayOfNulls<String>(flickrImages.size)
        for (i in 0 until flickrImages.size) {
            imgArray[i] = flickrImages[i]
        }

        var arr = arrayOfNulls<String>(flickrImages.size)
        for (i in 0 until flickrImages.size) {
            arr[i] = flickrImages[i]
        }
        arr.forEach {
            rocket.imageList.add(
                SlideModel(it)
            )
        }
        return rocket
    }
}