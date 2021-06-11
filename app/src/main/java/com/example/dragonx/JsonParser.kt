@file:Suppress("DEPRECATION")

package com.example.dragonx


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

import java.net.URL


class JsonParser(@SuppressLint("StaticFieldLeak") var activity:Activity, var context: Context) : AsyncTask<Void, String, Void>() {
    var rockets = arrayListOf<Rocket>()
    private lateinit var jsonArray: JsonArray<JsonObject>


    override fun doInBackground(vararg params: Void): Void? {
        val result = URL("https://api.spacexdata.com/v4/dragons").readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
        return null
    }

    override fun onPostExecute(result: Void?) {
        lateinit var rocket:Rocket
        super.onPostExecute(result)
        for (i in 0..jsonArray.size-1) {
            rocket = Rocket()
            rocket.name = jsonArray.string("name")[i]
            rocket.description = jsonArray.string("description")[i]
            rocket.link = jsonArray.string("wikipedia")[i]
            rocket.height = ("Diameter :\nmeters : ${jsonArray.obj("diameter")[i]?.double("meters")}\n" +
                    "feet : ${jsonArray.obj("diameter")[i]?.int("feet").toString()}")
            rocket.mass = ("Dry mass : ${jsonArray.int("dry_mass_kg")[i]} (${jsonArray.int("dry_mass_lb")[i]}) lb")
            rocket.year = jsonArray.string("first_flight")[i]
            rocket.image = jsonArray.string("flickr_images")[i]?.get(0).toString()
            rockets.add(rocket)

        }

        var listView = activity.findViewById<ListView>(R.id.listViewHome)
        val adapter : CustomAdapter = CustomAdapter(this.context, rockets)
        listView.adapter = adapter


    }
}