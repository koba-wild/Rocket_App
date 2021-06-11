
package com.example.dragonx

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Json

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    val rocketList: ArrayList<Rocket> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        JsonParser(this, this).execute()

    }
}

