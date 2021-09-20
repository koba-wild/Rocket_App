package com.example.dragonx.presentation.RocketList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dragonx.*
import com.example.dragonx.presentation.TopSpacingItemDecoration
import com.example.dragonx.models.Rocket
import com.example.dragonx.presentation.RocketDetails.RocketDetailsFragment
import com.example.dragonx.viewmodel.ApiStatus
import com.example.dragonx.viewmodel.RocketListViewModel


class RocketListFragment : Fragment(), OnRocketClickListener {

    companion object {
        private const val TAG = "KobaLOG"
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
        Log.d(TAG, "onCreateView RocketListFragment")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val errorPicture = view.findViewById<ImageView>(R.id.imageForError)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        recyclerView.addItemDecoration(topSpacingItemDecoration)
        val viewModel = ViewModelProvider(this).get(RocketListViewModel::class.java)

        viewModel.rocketsData.observe(viewLifecycleOwner, {
            recyclerView.adapter = RocketRecyclerAdapter(it, this)
        })
        viewModel.status.observe(viewLifecycleOwner, {
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
                }
            }
        })
        return view
    }

    override fun onRocketClick(rocket: Rocket, position : Int) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_rocket_list, RocketDetailsFragment.newInstance(position))
        transaction?.addToBackStack(null)?.commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart RocketListFragment")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume RocketListFragment")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause RocketListFragment")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop RocketListFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy RocketListFragment")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach RocketListFragment")
    }
}