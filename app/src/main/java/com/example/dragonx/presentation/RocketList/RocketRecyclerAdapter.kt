package com.example.dragonx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dragonx.presentation.RocketList.DiffCallback
import com.example.dragonx.model.data.RocketList

class RocketRecyclerAdapter(val clickListener: OnRocketClickListener
) : ListAdapter<RocketList, RecyclerView.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.rocket_list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RocketViewHolder -> {
                val item = getItem(position) as RocketList
                holder.bind(item, clickListener)
            }
        }
    }

    class RocketViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val rocketTitle: TextView = itemView.findViewById(R.id.rocket_title)
        val rocketYear: TextView = itemView.findViewById(R.id.rockets_year)
        val rocketsImage: ImageView = itemView.findViewById(R.id.rockets_image)
        fun bind(rocket: RocketList, action: OnRocketClickListener) {
            rocketTitle.setText(rocket.name)
            rocketYear.setText(rocket.firstFlight)
            Glide.with(itemView.context)
                    .load(rocket.flickrImages)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading_anim)
                    .error(R.drawable.ic_launcher_background)
                    .into(rocketsImage)
            itemView.setOnClickListener{
                action.onRocketClick(rocket, adapterPosition)
            }
        }
    }
}

interface OnRocketClickListener {
    fun onRocketClick(rocket: RocketList, position: Int)
}
