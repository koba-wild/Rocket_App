package com.example.dragonx.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dragonx.model.data.JsonObjects.Rocket

@Database(entities = [Rocket::class], version = 1,  exportSchema = false)
abstract class RocketDatabase : RoomDatabase() {
    abstract fun rocketDao() : RocketDao

    companion object {
        @Volatile
        private var INSTANCE: RocketDatabase? = null
        fun getInstance(context: Context): RocketDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RocketDatabase::class.java,
                        "rocket_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}