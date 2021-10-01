package com.example.dragonx.NetworkService

import retrofit2.Call
import retrofit2.http.GET

interface RemoteApiService {
    @GET("v4/dragons")
    suspend fun getRockets(): List<Rocket>
}