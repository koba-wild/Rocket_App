package com.example.dragonx.domain

import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.data.RocketList


class GetRocketList {
    companion object {
        fun getData(rockets: List<Rocket>?) : ArrayList<RocketList?> {
            val listOfRockets = arrayListOf<RocketList?>()
            if (rockets != null) {
                for (i in 0 until rockets.size) {
                    val name = rockets[i].name
                    val firstFlight = rockets[i].firstFlight
                    val images = rockets[i].flickrImages as ArrayList<String>
                    val flickrImages = images[0]
                    val rocket = RocketList(
                        name,
                        firstFlight, flickrImages
                    )
                    listOfRockets.add(rocket)
                }
            }
            return listOfRockets
        }
    }
}