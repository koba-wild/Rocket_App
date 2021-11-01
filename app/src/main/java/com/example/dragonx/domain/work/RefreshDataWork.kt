package com.example.dragonx.domain.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.dragonx.domain.Retrofit
import com.example.dragonx.domain.db.RocketDatabase
import com.example.dragonx.domain.db.RocketRepository
import retrofit2.HttpException


class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database = RocketDatabase.getInstance(applicationContext)
        val repository = RocketRepository(database.rocketDao(), Retrofit.buildApiService())
        return try {
            repository.getAllRockets()
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
}