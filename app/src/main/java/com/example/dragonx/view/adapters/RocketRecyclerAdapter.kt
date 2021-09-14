package com.example.dragonx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dragonx.databinding.RocketListLayoutBinding
import com.example.dragonx.models.Rocket

class RocketRecyclerAdapter(var rockets: List<Rocket>, var clickListener: OnRocketClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.rocket_list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RocketViewHolder -> {
                holder.bind(rockets[position], clickListener)
            }
        }
    }
    override fun getItemCount(): Int {
        return rockets.size
    }
    class RocketViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val binding = RocketListLayoutBinding.bind(itemView)
        val rocket_title: TextView = binding.rocketTitle
        val rocket_year: TextView = binding.rocketsYear
        val rockets_image: ImageView = binding.rocketsImage

        fun bind(rocket: Rocket, action: OnRocketClickListener) {
            rocket_title.setText(rocket.name)
            rocket_year.setText(rocket.first_flight)

            val requestOption = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                    .load(rocket.flickr_images)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(rockets_image)
            itemView.setOnClickListener{
                action.onRocketClick(rocket, adapterPosition)
            }
        }
    }
}
interface OnRocketClickListener {
    fun onRocketClick(rocket: Rocket, position: Int)
}
