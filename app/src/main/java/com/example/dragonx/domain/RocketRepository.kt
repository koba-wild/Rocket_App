package com.example.dragonx.domain

import com.example.dragonx.model.data.JsonObjects.Rocket

class RocketRepository {
    suspend fun downloadRocketsData(): List<Rocket> {
        return Retrofit
            .buildApiService()
            .getRockets()
    }
}