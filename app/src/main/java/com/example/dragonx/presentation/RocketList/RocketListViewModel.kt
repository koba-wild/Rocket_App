package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.example.dragonx.presentation.RocketDetails.RocketDetailsViewModel
import com.example.dragonx.presentation.RocketList.RocketListParser
import com.example.dragonx.util.RocketDetails
import com.example.dragonx.util.RocketTitle
import kotlinx.coroutines.*

class RocketListViewModel : ViewModel() {
    var rocketsData = MutableLiveData<List<RocketTitle>>()
    val rocketListParser = RocketListParser()

    init {
        getRockets()
    }

    fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rocketsData.postValue(rocketListParser.getRocketData() ?:null)
            }
        }
    }

}

