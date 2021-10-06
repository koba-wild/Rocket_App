package com.example.dragonx.presentation.RocketList

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dragonx.NetworkService.RetrofitInstance
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.util.ApiStatus
import com.example.dragonx.util.RocketTitle


class RocketListParser {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private suspend fun parseJson() : List<Rocket>? {
        try {
        _status.postValue(ApiStatus.LOADING)
        val rocketsList: List<Rocket> = RetrofitInstance
            .buildApiService()
            .getRockets()
        _status.postValue(ApiStatus.DONE)
        return rocketsList
        } catch (e: Exception) {
            _status.postValue(ApiStatus.ERROR)
            return null
        }
    }

    suspend fun getRocketData() : ArrayList<RocketTitle> {
        val listOfRockets = arrayListOf<RocketTitle>()
        val parsedData = parseJson()
        if (parsedData != null) {
            for (i in 0 until parsedData.size) {
                val name = parsedData[i].name
                val firstFlight = parsedData[i].firstFlight
                val images = parsedData[i].flickrImages as ArrayList<String>
                var flickrImages = images[0]
                val rocket = RocketTitle(name,
                    firstFlight, flickrImages)
                listOfRockets.add(rocket)
            }
        }
        return listOfRockets
    }
}