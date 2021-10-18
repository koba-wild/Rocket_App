package com.example.dragonx.domain

import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.data.RocketDetails

class GetRocketDetails {

    companion object {
        lateinit var rocketDetails: RocketDetails
        fun getData (rockets: List<Rocket>?, rocketPosition: Int): RocketDetails {
            if (rockets != null) {
                val name = rockets[rocketPosition].name
                val description = rockets[rocketPosition].description
                val wikipedia = rockets[rocketPosition].wikipedia
                val heightDiameter =
                    rockets[rocketPosition].diameter.meters
                val heightFeet = rockets[rocketPosition].diameter.feet
                val massKg =
                    rockets[rocketPosition].massKg
                val massLb = rockets[rocketPosition].massLb
                val firstFlight = rockets[rocketPosition].firstFlight
                val flickrImages = rockets[rocketPosition].flickrImages as List<String>

                rocketDetails = RocketDetails(
                    name, description, wikipedia,
                    heightDiameter, heightFeet, massKg, massLb, firstFlight, flickrImages
                )
            }
            return rocketDetails
        }
    }
}