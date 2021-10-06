package com.example.dragonx.presentation.RocketDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonx.util.RocketDetails
import kotlinx.coroutines.*

class RocketDetailsViewModel(private val rocketNumber: Int?) : ViewModel() {
    val rocketDetails = MutableLiveData<RocketDetails>()
    val rocketDetailsParser = RocketDetailsParser()

    init {
        getRockets()
    }

    fun getRockets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                rocketDetails.postValue(rocketDetailsParser.parseJson(rocketNumber)?:null)
            }
        }
    }
}