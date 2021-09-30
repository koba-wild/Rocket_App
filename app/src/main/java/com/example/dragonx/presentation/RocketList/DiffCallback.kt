package com.example.dragonx.presentation.RocketList

import androidx.recyclerview.widget.DiffUtil
import com.example.dragonx.NetworkService.Rocket
import com.example.dragonx.util.RocketModel

class DiffCallback : DiffUtil.ItemCallback<RocketModel>() {
    override fun areItemsTheSame(oldItem: RocketModel, newItem: RocketModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RocketModel, newItem: RocketModel): Boolean {
        return oldItem == newItem
    }
}
