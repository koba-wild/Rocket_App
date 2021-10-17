package com.example.dragonx.domain

import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.data.RocketList


class GetRocketList {

    suspend fun getRocketData() : ArrayList<RocketList> {
        val parsedData: List<Rocket> = Retrofit
            .buildApiService()
            .getRockets()
        val listOfRockets = arrayListOf<RocketList>()
        for (i in 0 until parsedData.size) {
            val name = parsedData[i].name
            val firstFlight = parsedData[i].firstFlight
            val images = parsedData[i].flickrImages as ArrayList<String>
            val flickrImages = images[0]
            val rocket = RocketList(name,
                firstFlight, flickrImages)
            listOfRockets.add(rocket)
        }
        return listOfRockets
    }
}