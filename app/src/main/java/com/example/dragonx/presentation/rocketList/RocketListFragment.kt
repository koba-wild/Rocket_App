package com.example.dragonx.presentation.rocketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels

import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.*
import com.example.dragonx.domain.db.RocketApplication
import com.example.dragonx.model.data.jsonObjects.Rocket
import com.example.dragonx.presentation.TopSpacingItemDecoration


class RocketListFragment : Fragment(), OnRocketClickListener {
    private val viewModel: RocketListViewModel by viewModels {
        ViewModelFactory((activity?.applicationContext as RocketApplication).repository)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RocketRecyclerAdapter
    private lateinit var errorPicture: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_list, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        errorPicture = view.findViewById<ImageView>(R.id.imageForError)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        recyclerView.addItemDecoration(topSpacingItemDecoration)

        adapter = RocketRecyclerAdapter(this)
        recyclerView.adapter = adapter
        errorPicture.isVisible = true
        errorPicture.setImageResource(R.drawable.loading_anim)

        viewModel.rocketsData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            errorPicture.isVisible = false
        })
        return view
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        viewModel.rocketsData.observe(viewLifecycleOwner, {
            val action = RocketListFragmentDirections
                .actionRocketListFragmentToRocketDetailsFragment(it[position])
            view?.findNavController()?.navigate(action)
        })
    }
}