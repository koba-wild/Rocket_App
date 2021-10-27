package com.example.dragonx.domain.db

import androidx.lifecycle.LiveData
import com.example.dragonx.model.data.JsonObjects.Rocket

class RocketRepository (private val rocketDao: RocketDao) {

    val getAllRockets: LiveData<List<Rocket>> = rocketDao.getAllRockets()

    suspend fun addRocket(rocket: Rocket) = rocketDao.addRocket(rocket)

    suspend fun getRocket(name: String) = rocketDao.getRocket(name)

    suspend fun update(rocket: Rocket) = rocketDao.update(rocket)

    suspend fun clear() = rocketDao.clear()

}