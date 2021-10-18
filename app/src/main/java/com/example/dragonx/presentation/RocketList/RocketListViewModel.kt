package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.example.dragonx.domain.GetRockets
import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.model.util.ApiStatus
import kotlinx.coroutines.*

class RocketListViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    var rocketsData = MutableLiveData<List<Rocket>?>()
    private val rocketData = GetRockets()
    lateinit var myException: Exception

    init {
        getRockets()
    }

    private fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    _status.postValue(ApiStatus.LOADING)
                    rocketsData.postValue(rocketData.getDataFromApi())
                    _status.postValue(ApiStatus.DONE)
                } catch (e: Exception) {
                    _status.postValue(ApiStatus.ERROR)
                    myException = e
                }
            }
        }
    }
}

