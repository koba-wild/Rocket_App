
package com.example.dragonx.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dragonx.R
import com.example.dragonx.presentation.RocketList.RocketListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_rocket_list,
                RocketListFragment.newInstance()
            ).addToBackStack(null).commit()
        }
    }
}

