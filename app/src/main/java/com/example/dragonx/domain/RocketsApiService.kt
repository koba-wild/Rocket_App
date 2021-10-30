package com.example.dragonx.domain


import com.example.dragonx.model.data.jsonObjects.Rocket
import retrofit2.http.GET

interface RocketsApiService {
    @GET("v4/dragons")
    suspend fun getRockets(): List<Rocket>
}