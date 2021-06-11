package com.example.dragonx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dragonx.databinding.ActivityMainBinding

class RocketDescription : AppCompatActivity() {
    lateinit var MainBinding: ActivityMainBinding
    val MY_MAIN_ROCKETS = "https://api.spacexdata.com/v4/dragons"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainBinding.root)


    }
}