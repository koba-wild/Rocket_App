package com.example.dragonx.domain.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dragonx.model.data.jsonObjects.Rocket

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllRockets(rockets: List<Rocket>)

    @Query("DELETE from rockets_table")
    fun clear()

    @Query("SELECT * FROM rockets_table ORDER BY name ASC")
    fun getAllRockets(): LiveData<List<Rocket>>

}