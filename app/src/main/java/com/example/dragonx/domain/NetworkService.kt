package com.example.dragonx.domain

import com.example.dragonx.model.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun buildApiService(): RemoteApiService =
        buildRetrofit().create(RemoteApiService::class.java)

}