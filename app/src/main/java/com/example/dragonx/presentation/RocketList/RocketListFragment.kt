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
import com.example.dragonx.presentation.TopSpacingItemDecoration
import com.example.dragonx.model.util.ApiStatus
import com.example.dragonx.model.data.RocketList
import com.example.dragonx.viewmodel.RocketListViewModel


class RocketListFragment : Fragment(), OnRocketClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
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
        val viewModel = ViewModelProvider(this).get(RocketListViewModel::class.java)
        val adapter = RocketRecyclerAdapter(this)
        recyclerView.adapter = adapter
        viewModel.rocketsData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.ERROR -> {
                    errorPicture.isVisible = true
                    errorPicture.setImageResource(R.drawable.ic_connection_error)
                    Toast.makeText(context, "Exception occurred: ${viewModel.myException}", Toast.LENGTH_LONG).show()
                }
                ApiStatus.DONE -> {
                    errorPicture.visibility = View.GONE
                }
                else -> {
                    errorPicture.isVisible = true
                    errorPicture.setImageResource(R.drawable.loading_anim)
                }
            }
        })
        return view
    }

    override fun onRocketClick(rocket: RocketList, position : Int) {
        val action = RocketListFragmentDirections.actionRocketListFragmentToRocketDetailsFragment(position)
        view?.findNavController()?.navigate(action)
    }
}