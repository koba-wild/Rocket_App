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
    private val rocketListParser = GetRocketList()
    lateinit var myException: Exception

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
                } catch (e: Exception) {
                    _status.postValue(ApiStatus.ERROR)
                    myException = e
                }
            }
        }
    }

}

