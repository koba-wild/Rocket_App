package com.example.dragonx.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.dragonx.domain.RocketLoader
import com.example.dragonx.domain.db.RocketDatabase
import com.example.dragonx.domain.db.RocketRepository
import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.util.StatusChecker
import kotlinx.coroutines.*

class RocketListViewModel(application: Application) : AndroidViewModel(application) {

    val readAllRocketData: LiveData<List<Rocket>>
    private val repository: RocketRepository

    private val _status = MutableLiveData<StatusChecker<List<Rocket>>>()
    val status: LiveData<StatusChecker<List<Rocket>>>
        get() = _status
    var _rocketsData = MutableLiveData<List<Rocket>>()
    val rocketsData: LiveData<List<Rocket>>
        get() = _rocketsData
    private val rocketLoader = RocketLoader()

    init {
        val rocketDao = RocketDatabase.getInstance(application).rocketDao()
        repository = RocketRepository(rocketDao)
        readAllRocketData = repository.getAllRockets
        downloadRockets()
    }

    private fun addRocket(rocket: Rocket) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRocket(rocket)
        }
    }

    private fun downloadRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (readAllRocketData.value?.isEmpty() == true) {
                    try {
                        _rocketsData.postValue(rocketLoader.downloadRocketsData())
                        _status.postValue(StatusChecker.Done(rocketLoader.downloadRocketsData()))
                        rocketsData.value?.forEach {
                            addRocket(it)
                        }
                    } catch (e: Exception) {
                        _status.postValue(StatusChecker.Error(e))
                    }
                } else {
                    try {
                        _status.postValue(StatusChecker.Done(readAllRocketData.value!!))
                    } catch (e: Exception) {
                        _status.postValue(StatusChecker.Error(e))
                    }
                }
            }
        }
    }
}

//class ViewModelFactory(private val repository: RocketRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(RocketListViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return RocketListViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

