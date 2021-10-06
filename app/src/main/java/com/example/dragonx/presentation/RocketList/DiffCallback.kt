package com.example.dragonx.presentation.RocketList

import androidx.recyclerview.widget.DiffUtil
import com.example.dragonx.util.RocketDetails
import com.example.dragonx.util.RocketTitle

class DiffCallback : DiffUtil.ItemCallback<RocketTitle>() {
    override fun areItemsTheSame(oldItem: RocketTitle, newItem: RocketTitle): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RocketTitle, newItem: RocketTitle): Boolean {
        return oldItem == newItem
    }
}
