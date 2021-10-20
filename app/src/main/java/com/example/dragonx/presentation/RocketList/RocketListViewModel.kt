package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.example.dragonx.domain.GetRockets
import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.util.StatusChecker
import kotlinx.coroutines.*

class RocketListViewModel : ViewModel() {
    private val _status = MutableLiveData<StatusChecker<List<Rocket>?>>()
    val status: LiveData<StatusChecker<List<Rocket>?>>
        get() = _status
    var _rocketsData = MutableLiveData<List<Rocket>?>()
    val rocketsData: LiveData<List<Rocket>?>
        get() = _rocketsData
    private val rocketData = GetRockets()

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    _rocketsData.postValue(rocketData.getDataFromApi())
                    _status.postValue(StatusChecker.Done(rocketData.getDataFromApi()))
                } catch (e: Exception) {
                    _status.postValue(StatusChecker.Error(e))
                }
            }
        }
    }
}

