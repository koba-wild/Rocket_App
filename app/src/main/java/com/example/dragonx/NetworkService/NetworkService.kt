package com.example.dragonx.NetworkService

import com.example.dragonx.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService {
    companion object {
        fun getInstance(): NetworkService {
            val instance = NetworkService()
            return instance
        }
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    fun buildApiService(): RemoteApiService =
        buildRetrofit().create(RemoteApiService::class.java)

}