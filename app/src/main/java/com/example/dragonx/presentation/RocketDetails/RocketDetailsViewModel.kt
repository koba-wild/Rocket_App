package com.example.dragonx.presentation.RocketDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import java.net.URL

class RocketDetailsViewModel(private val rocketNumber: Int?) : ViewModel() {
    val rocketDetails = MutableLiveData<Rocket>()
    private val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    fun getRockets() {
        coroutineScope.launch {
            rocketDetails.postValue(parseJson(rocketNumber)!!)
        }
    }

    init {
        getRockets()
    }

    suspend fun parseJson (rocketNumber:Int?): Rocket {
        lateinit var jsonArray: JsonArray<JsonObject>
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
        val rocket = Rocket()
        rocket.name = jsonArray.string("name")[rocketNumber!!]
        rocket.description = jsonArray.string("description")[rocketNumber]
        rocket.wikipedia = jsonArray.string("wikipedia")[rocketNumber]
        rocket.height =
            ("Diameter : ${jsonArray.obj("diameter")[rocketNumber]?.double("meters")}," +
                    " feet : ${jsonArray.obj("diameter")[rocketNumber]?.int("feet").toString()}")
        rocket.mass =
            ("Dry mass : ${jsonArray.int("dry_mass_kg")[rocketNumber]} (${jsonArray.int("dry_mass_lb")[0]}) lb")

        rocket.first_flight = jsonArray.string("first_flight")[rocketNumber]
        val flickr_images = jsonArray.get("flickr_images")[rocketNumber] as JsonArray<String>
        var arr = arrayOfNulls<String>(flickr_images.size)
        for (i in 0 until flickr_images.size) {
            arr[i] = flickr_images[i]
        }
        arr.forEach {
            rocket.imageList.add(
                SlideModel(it)
            )
        }
        return rocket
    }

    override fun onCleared() {
        super.onCleared()
        completableJob.cancel()
    }
}