package com.example.dragonx.domain.db

import android.app.Application
import com.example.dragonx.domain.Retrofit
import com.example.dragonx.domain.RocketsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RocketApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { RocketDatabase.getInstance(this, applicationScope) }
    val repository by lazy { RocketRepository(database.rocketDao(), Retrofit.buildApiService()) }
}