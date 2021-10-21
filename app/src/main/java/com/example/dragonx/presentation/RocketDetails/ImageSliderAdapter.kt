package com.example.dragonx.presentation.RocketDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dragonx.R
import com.example.dragonx.presentation.RocketList.DiffCallback
import com.google.android.material.imageview.ShapeableImageView

class ImageSliderAdapter : ListAdapter<String, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> {
                val item = getItem(position) as String
                holder.bind(item)
            }
        }
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sliderImageView = itemView.findViewById<ShapeableImageView>(R.id.sliderImageView)
        fun bind(image: String) {
            Glide.with(itemView.context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_anim)
                .error(R.drawable.ic_launcher_background)
                .into(sliderImageView)
        }
    }
}