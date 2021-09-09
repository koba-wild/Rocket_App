
package com.example.dragonx.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dragonx.R
import com.example.dragonx.view.fragments.RocketDetailsFragment
import com.example.dragonx.view.fragments.RocketListFragment

class MainActivity : AppCompatActivity() {
    private val fragmentRocketList = RocketListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_rocket_list,
            fragmentRocketList).commit()
    }
}

