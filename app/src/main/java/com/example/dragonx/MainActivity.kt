
package com.example.dragonx

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.dragonx.databinding.ActivityMainBinding
import com.example.dragonx.models.Rocket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.text.ParsePosition

@Suppress("DEPRECATION", "BlockingMethodInNonBlockingContext")
class MainActivity : AppCompatActivity(), OnRocketClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        JsonParser(this, this).execute()
    }
    inner class JsonParser(@SuppressLint("StaticFieldLeak") var activity: Activity, var context: Context) : AsyncTask<Void, String, Void>() {
        var rockets = arrayListOf<Rocket>()
        val images = arrayListOf("https://live.staticflickr.com/8578/16655995541_7817565ea9_k.jpg", "https://farm8.staticflickr.com/7647/16581815487_6d56cb32e1_b.jpg")
        private lateinit var jsonArray: JsonArray<JsonObject>

        override fun doInBackground(vararg params: Void): Void? {
            val result = URL("https://api.spacexdata.com/v4/dragons").readText()
            val parser: Parser = Parser()
            val stringBuilder: StringBuilder = StringBuilder(result)
            jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
            return null
        }
        override fun onPostExecute(result: Void?) {
            lateinit var rocket: Rocket
            super.onPostExecute(result)
            for (i in 0 until jsonArray.size) {
                rocket = Rocket()
                rocket.name = jsonArray.string("name")[i]
                rocket.year = jsonArray.string("first_flight")[i]
                rocket.image = URL(images[i])
                rockets.add(rocket)
            }
            initRecyclerView()
        }

        private fun initRecyclerView() {
            var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            recyclerView.addItemDecoration(topSpacingItemDecoration)
            recyclerView.adapter = RocketRecyclerAdapter(rockets,this@MainActivity)
        }
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        if (position==0) {
            val intent = Intent(this, RocketDetails::class.java)
            intent.putExtra("rocket", 0)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, RocketDetails::class.java)
            intent.putExtra("rocket", 1)
            startActivity(intent)
        }
    }
}

