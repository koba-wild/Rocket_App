package com.example.dragonx.domain

import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.model.data.RocketDetails

class GetRocketDetails {
    suspend fun parseJson (rocketNumber:Int?): RocketDetails {
        val imageList = ArrayList<SlideModel>()
        val rocketsList: List<Rocket> = Retrofit
            .buildApiService()
            .getRockets()
        val name = rocketsList[rocketNumber!!].name
        val description = rocketsList[rocketNumber].description
        val wikipedia = rocketsList[rocketNumber].wikipedia
        val heightDiameter =
            rocketsList[rocketNumber].diameter.meters
        val heightFeet = rocketsList[rocketNumber].diameter.feet
        val massKg =
            rocketsList[rocketNumber].massKg
        val massLb = rocketsList[rocketNumber].massLb
        val firstFlight = rocketsList[rocketNumber].firstFlight
        val flickrImages = rocketsList[rocketNumber].flickrImages as ArrayList<String>
        val imgArray = arrayOfNulls<String>(flickrImages.size)
        for (i in 0 until flickrImages.size) {
            imgArray[i] = flickrImages[i]
        }

        val arr = arrayOfNulls<String>(flickrImages.size)
        for (i in 0 until flickrImages.size) {
            arr[i] = flickrImages[i]
        }
        arr.forEach {
            imageList.add(
                SlideModel(it)
            )
        }
        val rocket = RocketDetails(
            name, description, wikipedia,
            heightDiameter, heightFeet, massKg, massLb, firstFlight, imageList)
        return rocket
    }
}