package com.example.dragonx.presentation.RocketDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.*

import java.net.URL

class RocketDetailsViewModel(private val rocketNumber: Int?) : ViewModel() {
    val rocketDetails = MutableLiveData<Rocket>()
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