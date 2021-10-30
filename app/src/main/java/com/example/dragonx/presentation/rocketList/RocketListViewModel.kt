package com.example.dragonx.presentation.rocketList

import androidx.lifecycle.*
import com.example.dragonx.domain.db.RocketRepository
import com.example.dragonx.model.data.jsonObjects.Rocket
import com.example.dragonx.model.util.StatusChecker
import kotlinx.coroutines.*

class RocketListViewModel(private val repository: RocketRepository) : ViewModel() {

    val rocketsData: LiveData<List<Rocket>> = repository.getAllRockets()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val rockets = repository.getRemoteRockets()
            repository.addAllRockets(rockets)
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

