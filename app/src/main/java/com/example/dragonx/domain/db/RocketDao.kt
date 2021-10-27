package com.example.dragonx.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dragonx.model.data.JsonObjects.Rocket

@Dao
interface RocketDao {
    @Insert
    fun addRocket(rocket: Rocket)

    @Update
    fun update(rocket: Rocket)

    @Query("SELECT * from rockets_table WHERE name = :name")
    fun getRocket(name: String): Rocket?

    @Query("DELETE from rockets_table")
    fun clear()

    @Query("SELECT * FROM rockets_table ORDER BY name DESC")
    fun getAllRockets(): LiveData<List<Rocket>>

}