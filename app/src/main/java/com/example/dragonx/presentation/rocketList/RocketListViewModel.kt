package com.example.dragonx.presentation.rocketList

import androidx.lifecycle.*
import com.example.dragonx.domain.RetrofitService
import com.example.dragonx.domain.db.RocketRepository
import com.example.dragonx.model.data.jsonObjects.Rocket
import com.example.dragonx.model.util.StatusChecker
import kotlinx.coroutines.*

class RocketListViewModel(private val repository: RocketRepository) : ViewModel() {

//    val readAllRocketData: LiveData<List<Rocket>> = repository.getAllRockets()

    private val _status = MutableLiveData<StatusChecker<List<Rocket>>>()
    val status: LiveData<StatusChecker<List<Rocket>>>
        get() = _status
    var _rocketsData = MutableLiveData<List<Rocket>>()
    val rocketsData: LiveData<List<Rocket>>
        get() = _rocketsData

    init {
        downloadRockets()
    }

    private fun downloadRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _rocketsData.postValue(repository.getAllRockets().value!!)
                    _status.postValue(StatusChecker.Done(_rocketsData.value!!))
                } catch (e: Exception) {
                    _status.postValue(StatusChecker.Error(e))
                }
            }
        }
    }
}

class ViewModelFactory(private val repository: RocketRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RocketListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RocketListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

