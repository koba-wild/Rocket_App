package com.example.dragonx.viewmodel

import androidx.lifecycle.*
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.presentation.RocketDetails.RocketDetailsViewModel
import com.example.dragonx.models.Rocket
import com.example.dragonx.util.Constants.Companion.URL_STRING
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import java.net.URL
import kotlin.Exception

enum class ApiStatus {LOADING, ERROR, DONE}

class RocketListViewModel : ViewModel() {
    var rocketsData = MutableLiveData<List<Rocket>>()
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    fun getRockets() {
        coroutineScope.launch {
            rocketsData.postValue(getRocketData() ?:null)
        }
    }

    init {
        getRockets()
    }

    suspend fun parseJson () : JsonArray<JsonObject>? {
        val parser: Parser = Parser()
        try {
            _status.postValue(ApiStatus.LOADING)
            val result = URL(URL_STRING).readText()
            val stringBuilder: StringBuilder = StringBuilder(result)
            val myParsedObject = parser.parse(stringBuilder) as JsonArray<JsonObject>
            _status.postValue(ApiStatus.DONE)
            return myParsedObject
        } catch (e: Exception){
            _status.postValue(ApiStatus.ERROR)
            return null
        }
    }

    suspend fun getRocketData(): ArrayList<Rocket> {
        val rockets = arrayListOf<Rocket>()
        val parsedData = parseJson()
        if (parsedData != null) {
            for (i in 0 until parsedData.size) {
                val rocket = Rocket()
                rocket.name = parsedData.string("name")[i]
                rocket.first_flight = parsedData.string("first_flight")[i]
                var flickr_images = parsedData["flickr_images"][i] as JsonArray<String>
                rocket.flickr_images = flickr_images[0]
                rockets.add(rocket)
            }
        }
        return rockets
    }

    override fun onCleared() {
        super.onCleared()
        completableJob.cancel()

    }
}


class ViewModelFactory(private val rocketNumber: Int?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RocketDetailsViewModel(rocketNumber) as T
    }
}