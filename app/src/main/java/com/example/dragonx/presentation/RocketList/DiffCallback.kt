package com.example.dragonx.presentation.RocketList

import android.content.ClipData
import androidx.recyclerview.widget.DiffUtil
import com.example.dragonx.models.Rocket

class DiffCallback : DiffUtil.ItemCallback<Rocket>() {
    override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean {
        return oldItem == newItem
    }
}
