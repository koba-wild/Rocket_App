package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.example.dragonx.presentation.RocketDetails.RocketDetailsViewModel
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.presentation.RocketList.RocketListParser
import com.example.dragonx.util.RocketModel
import kotlinx.coroutines.*

class RocketListViewModel : ViewModel() {
    var rocketsData = MutableLiveData<List<RocketModel>>()
    val rocketListParser = RocketListParser()
    fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rocketsData.postValue(rocketListParser.getRocketData() ?:null)
            }
        }
    }

    init {
        getRockets()
    }
}

class ViewModelFactory(private val rocketNumber: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RocketDetailsViewModel(rocketNumber) as T
    }
}