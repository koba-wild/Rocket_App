package com.example.dragonx.presentation.RocketList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dragonx.presentation.RocketDetails.RocketDetailsViewModel

class ViewModelFactory(private val rocketNumber: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RocketDetailsViewModel(rocketNumber) as T
    }
}