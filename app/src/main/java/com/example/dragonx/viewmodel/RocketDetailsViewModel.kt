package com.example.dragonx.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.models.Rocket

import java.net.URL

class RocketDetailsViewModel : ViewModel() {
    var imageList = ArrayList<SlideModel>()


    init {
        Log.i("JsonParser", "JsonParser for RocketDetailsFragment was created")
    }

    suspend fun parseJson (rocketNumber:Int?): Rocket {
        lateinit var rocket: Rocket
        lateinit var jsonArray: JsonArray<JsonObject>

        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
        rocket = Rocket()
        rocket.name = jsonArray.string("name")[rocketNumber!!]
        rocket.description = jsonArray.string("description")[rocketNumber]
        rocket.link = jsonArray.string("wikipedia")[rocketNumber]
        rocket.height =
            ("Diameter : meters : ${jsonArray.obj("diameter")[rocketNumber]?.double("meters")}," +
                    " feet : ${jsonArray.obj("diameter")[rocketNumber]?.int("feet").toString()}")
        rocket.mass =
            ("Dry mass : ${jsonArray.int("dry_mass_kg")[rocketNumber]} (${jsonArray.int("dry_mass_lb")[0]}) lb")

        rocket.year = jsonArray.string("first_flight")[rocketNumber]
        return rocket
    }

    fun imagesForSlider(rocketNumber:Int?): ArrayList<SlideModel> {
        when(rocketNumber) {
            0 -> {  imageList.add(
                SlideModel(
                    "https://live.staticflickr.com/8578/16655995541_7817565ea9_k.jpg",
                    "Dragon1 pic.1")
            )
                imageList.add(
                    SlideModel(
                        "https://farm3.staticflickr.com/2815/32761844973_4b55b27d3c_b.jpg",
                        "Dragon1 pic.2")
                )
                imageList. add (
                    SlideModel(
                        "https://farm9.staticflickr.com/8618/16649075267_d18cbb4342_b.jpg",
                        "Dragon1 pic.3")
                )
            }
            1 -> {
                imageList.add(
                    SlideModel(
                        "https://farm8.staticflickr.com/7647/16581815487_6d56cb32e1_b.jpg",
                        "Dragon2 pic.1")
                )
                imageList.add(
                    SlideModel(
                        "https://farm1.staticflickr.com/780/21119686299_c88f63e350_b.jpg",
                        "Dragon2 pic.2")
                )
                imageList.add(
                    SlideModel(
                        "https://farm9.staticflickr.com/8588/16661791299_a236e2f5dc_b.jpg",
                        "Dragon2 pic.3")
                )
            }
        }
        return imageList
    }
}