package com.example.dragonx.presentation.RocketList

import androidx.recyclerview.widget.DiffUtil
import com.example.dragonx.model.data.RocketList

class DiffCallback : DiffUtil.ItemCallback<RocketList>() {
    override fun areItemsTheSame(oldItem: RocketList, newItem: RocketList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RocketList, newItem: RocketList): Boolean {
        return oldItem == newItem
    }
}
