package com.example.dragonx.domain

import com.example.dragonx.NetworkService.Rocket
import retrofit2.http.GET

interface RemoteApiService {
    @GET("v4/dragons")
    suspend fun getRockets(): List<Rocket>
}