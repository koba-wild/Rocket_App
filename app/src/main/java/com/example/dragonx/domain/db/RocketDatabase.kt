package com.example.dragonx.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dragonx.domain.RetrofitService
import com.example.dragonx.model.data.jsonObjects.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Rocket::class], version = 1,  exportSchema = true)
@TypeConverters(TypeConverter::class)
abstract class RocketDatabase : RoomDatabase() {
    abstract fun rocketDao() : RocketDao

    private class RocketDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var rocketDao = database.rocketDao()
                    val rocketLoader = RetrofitService()
                    val downloadedRockets = rocketLoader.downloadRocketsData()
                    downloadedRockets.forEach {
                        rocketDao.addRocket(it)
                    }
                }
            }
        }
    }

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
                    .addCallback(RocketDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}