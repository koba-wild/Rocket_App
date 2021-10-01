package com.example.dragonx.presentation.RocketList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dragonx.NetworkService.NetworkService
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.NetworkService.Rockets
import com.example.dragonx.util.RocketModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ApiStatus {LOADING, ERROR, DONE}

class RocketListParser {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private suspend fun parseJson() : List<Rocket>? {
        try {
        _status.postValue(ApiStatus.LOADING)
        val rocketsList: List<Rocket> = NetworkService.getInstance()
            .buildApiService()
            .getRockets()
        _status.postValue(ApiStatus.DONE)
        return rocketsList
        } catch (e: Exception) {
            _status.postValue(ApiStatus.ERROR)
            return null
        }
    }

    suspend fun getRocketData() : ArrayList<RocketModel> {
        val listOfRockets = arrayListOf<RocketModel>()
        val parsedData = parseJson()
        if (parsedData != null) {
            for (i in 0 until parsedData.size) {
                val rocket = RocketModel()
                rocket.name = parsedData[i].name
                rocket.firstFlight = parsedData[i].firstFlight
                val images = parsedData[i].flickrImages as ArrayList<String>
                rocket.flickrImages = images[0]
                listOfRockets.add(rocket)
            }
        }
        return listOfRockets
    }
}