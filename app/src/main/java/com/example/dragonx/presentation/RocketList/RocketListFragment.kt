package com.example.dragonx.presentation.RocketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.*
import com.example.dragonx.model.data.JsonObjects.Rocket
import com.example.dragonx.presentation.TopSpacingItemDecoration
import com.example.dragonx.model.util.StatusChecker
import com.example.dragonx.viewmodel.RocketListViewModel


class RocketListFragment : Fragment(), OnRocketClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(RocketListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.fragment_rocket_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val errorPicture = view.findViewById<ImageView>(R.id.imageForError)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        recyclerView.addItemDecoration(topSpacingItemDecoration)

        val adapter = RocketRecyclerAdapter(this)
        recyclerView.adapter = adapter
        errorPicture.isVisible = true
        errorPicture.setImageResource(R.drawable.loading_anim)

        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                is StatusChecker.Error -> {
                    errorPicture.isVisible = true
                    errorPicture.setImageResource(R.drawable.ic_connection_error)
                    Toast.makeText(context, getString(R.string.error_warning, it.myException), Toast.LENGTH_LONG).show()
                }
                is StatusChecker.Done -> {
                    adapter.submitList(it.data)
                    errorPicture.visibility = View.GONE
                }
            }
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