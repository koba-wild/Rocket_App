package com.example.dragonx.domain

import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.data.RocketDetails

class GetRockets {
    suspend fun getDataFromApi(): List<Rocket>? {
        val rocketsData: List<Rocket> = Retrofit
            .buildApiService()
            .getRockets()
        return rocketsData
    }
}