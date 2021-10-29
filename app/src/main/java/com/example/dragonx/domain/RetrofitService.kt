package com.example.dragonx.domain

import com.example.dragonx.model.data.jsonObjects.Rocket

class RetrofitService {
    suspend fun downloadRocketsData(): List<Rocket> {
        return Retrofit
            .buildApiService()
            .getRockets()
    }
}