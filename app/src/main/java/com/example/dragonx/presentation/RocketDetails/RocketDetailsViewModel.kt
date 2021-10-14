package com.example.dragonx.presentation.RocketDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dragonx.domain.GetRocketDetails
import com.example.dragonx.model.data.RocketDetails
import kotlinx.coroutines.*

class RocketDetailsViewModel(private val rocketNumber: Int?) : ViewModel() {
    val rocketDetails = MutableLiveData<RocketDetails>()
    private val rocketDetailsParser = GetRocketDetails()

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