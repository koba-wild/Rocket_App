package com.example.dragonx.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dragonx.model.data.jsonObjects.Rocket
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Rocket::class], version = 1,  exportSchema = true)
@TypeConverters(TypeConverter::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao() : RocketDao


    companion object {
        @Volatile
        private var INSTANCE: RocketDatabase? = null

        fun getInstance(
            context: Context, scope: CoroutineScope
        ): RocketDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RocketDatabase::class.java,
                    "rockets_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}