package com.example.dragonx.domain.db

import com.example.dragonx.model.data.jsonObjects.Rocket

class RocketRepository (private val rocketDao: RocketDao) {

    fun getAllRockets() = rocketDao.getAllRockets()

    suspend fun addRocket(rocket: Rocket) = rocketDao.addRocket(rocket)

    suspend fun getRocket(name: String) = rocketDao.getRocket(name)

    suspend fun update(rocket: Rocket) = rocketDao.update(rocket)

    suspend fun clear() = rocketDao.clear()

}