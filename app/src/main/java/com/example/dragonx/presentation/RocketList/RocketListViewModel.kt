package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.example.dragonx.domain.GetRocketList
import com.example.dragonx.model.data.RocketList
import com.example.dragonx.model.util.ApiStatus
import kotlinx.coroutines.*

class RocketListViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    var rocketsData = MutableLiveData<List<RocketList>>()
    val rocketListParser = GetRocketList()

    init {
        getRockets()
    }

    fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    _status.postValue(ApiStatus.LOADING)
                    rocketsData.postValue(rocketListParser.getRocketData() ?:null)
                    _status.postValue(ApiStatus.DONE)
                } catch (e: IllegalAccessError) {
                    _status.postValue(ApiStatus.ERROR)
                }
            }
        }
    }

}

