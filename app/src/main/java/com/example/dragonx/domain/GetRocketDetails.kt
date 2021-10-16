package com.example.dragonx.domain

import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.model.data.RocketDetails

class GetRocketDetails {
    suspend fun parseJson (rocketNumber:Int?): RocketDetails {
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
        val flickrImages = rocketsList[rocketNumber].flickrImages as List<String>


        val rocket = RocketDetails(
            name, description, wikipedia,
            heightDiameter, heightFeet, massKg, massLb, firstFlight, flickrImages)
        return rocket
    }
}