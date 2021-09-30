package com.example.dragonx.presentation.RocketDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.util.RocketModel
import kotlinx.coroutines.*

class RocketDetailsViewModel(private val rocketNumber: Int?) : ViewModel() {
    val rocketDetails = MutableLiveData<RocketModel>()
    val rocketDetailsParser = RocketDetailsParser()

    fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rocketDetails.postValue(rocketDetailsParser.parseJson(rocketNumber)!!)
            }
        }
    }

    init {
        getRockets()
    }
}