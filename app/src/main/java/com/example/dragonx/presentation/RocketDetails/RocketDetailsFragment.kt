package com.example.dragonx.presentation.RocketDetails

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.dragonx.R
import com.example.dragonx.RocketRecyclerAdapter
import com.example.dragonx.presentation.RocketList.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_rocket_details.view.*
import com.example.dragonx.model.util.Constants.Companion.ROCKET_NUMBER
import com.example.dragonx.model.data.RocketDetails


class RocketDetailsFragment : Fragment() {
    private val args: RocketDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rocket_details, container, false)
        val imageSlider = view.findViewById<ViewPager2>(R.id.viewPager)
        val rocketNumber = args.rocketNumber
        val viewModelFactory = ViewModelFactory(rocketNumber)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        val adapter = ImageSliderAdapter()
        imageSlider.adapter = adapter
        viewModel.rocketDetails.observe(viewLifecycleOwner, {it: RocketDetails ->
            view.rocketName.text = it.name
            view.description.text = it.description
            view.wikiLink.text = it.wikipedia
            view.heightRocket.text = getString(R.string.rocket_diameter, it.heightDiameter, it.heightFeet )
            view.mass.text = getString(R.string.rocket_mass, it.massKg, it.massLb)
            view.year.text = it.firstFlight
            adapter.submitList(it.flickrImages)
            var dots: Array<TextView?> = arrayOfNulls(it.flickrImages.size)
            var onImageChangeCallback = object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(state: Int) {
                    super.onPageScrollStateChanged(state)
                    addDotView(state, dots, view, it.flickrImages.size)
                }
            }
            view.viewPager.registerOnPageChangeCallback(onImageChangeCallback)
        })
        return view
    }

    private fun addDotView(currentPage: Int, dots: Array<TextView?>, view: View, images: Int) {
        view.layoutDots.removeAllViews()
        for (i in 0 until images) {
            dots[i] = TextView(activity)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i]?.text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
            } else {
                dots[i]?.text = Html.fromHtml("&#8226")
            }
            dots[i]?.textSize = 38f
            dots[i]?.setTextColor(ContextCompat.getColor(requireActivity().applicationContext, R.color.white))
            view.layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[currentPage]?.setTextColor(ContextCompat.getColor(requireActivity().applicationContext, R.color.teal_700))
        }
    }
}