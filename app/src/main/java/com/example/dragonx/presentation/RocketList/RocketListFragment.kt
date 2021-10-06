package com.example.dragonx.presentation.RocketList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.*
import com.example.dragonx.presentation.MainActivity
import com.example.dragonx.presentation.TopSpacingItemDecoration
import com.example.dragonx.presentation.RocketDetails.RocketDetailsFragment
import com.example.dragonx.util.ApiStatus
import com.example.dragonx.util.RocketDetails
import com.example.dragonx.util.RocketTitle
import com.example.dragonx.viewmodel.RocketListViewModel


class RocketListFragment : Fragment(), OnRocketClickListener {

    companion object {
        private const val TAG = "RocketList Logs"
            fun newInstance(): RocketListFragment {
                val fragment = RocketListFragment()
                return fragment
            }
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

        viewModel.rocketListParser.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.ERROR -> {
                    errorPicture.visibility = View.VISIBLE
                    errorPicture.setImageResource(R.drawable.ic_connection_error)
                }
                ApiStatus.DONE -> {
                    errorPicture.visibility = View.GONE
                }
                else -> {
                    errorPicture.visibility = View.VISIBLE
                    errorPicture.setImageResource(R.drawable.loading_anim)
                    Toast.makeText(context, "Data`s failure occurred:(", Toast.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    override fun onRocketClick(rocket: RocketTitle, position : Int) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_rocket_list, RocketDetailsFragment.newInstance(position))
        transaction?.addToBackStack(null)?.commit()
    }
}