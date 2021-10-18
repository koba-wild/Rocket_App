package com.example.dragonx.presentation.RocketDetails

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.dragonx.R


class RocketDetailsFragment : Fragment() {
    private val args: RocketDetailsFragmentArgs by navArgs()
    private val rocketName: TextView by lazy { requireView().findViewById(R.id.rocketName) }
    private val description: TextView by lazy { requireView().findViewById(R.id.description) }
    private val wikiLink: TextView by lazy { requireView().findViewById(R.id.wikiLink) }
    private val heightRocket: TextView by lazy { requireView().findViewById(R.id.heightRocket) }
    private val mass: TextView by lazy { requireView().findViewById(R.id.mass) }
    private val year: TextView by lazy { requireView().findViewById(R.id.year) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rocket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageSlider = view.findViewById<ViewPager2>(R.id.viewPager)
//        val rocketName = view.findViewById<TextView>(R.id.rocketName)
//        val description = view.findViewById<TextView>(R.id.description)
//        val wikiLink = view.findViewById<TextView>(R.id.wikiLink)
//        val heightRocket = view.findViewById<TextView>(R.id.heightRocket)
//        val mass = view.findViewById<TextView>(R.id.mass)
//        val year = view.findViewById<TextView>(R.id.year)
        setHasOptionsMenu(true)
        val adapter = ImageSliderAdapter()
        val rocketDetails = args.rocketDetails
        rocketName.text = rocketDetails.name
        description.text = rocketDetails.description
        wikiLink.text = rocketDetails.wikipedia
        heightRocket.text = getString(
            R.string.rocket_diameter, rocketDetails.heightDiameter, rocketDetails.heightFeet)
        mass.text = getString(R.string.rocket_mass, rocketDetails.massKg, rocketDetails.massLb)
        year.text = rocketDetails.firstFlight
        adapter.submitList(rocketDetails.flickrImages)
        imageSlider.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    private fun getShareIntent(): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(
                R.string.your_rocket, rocketName.text, description.text, wikiLink.text,
                heightRocket.text,
                mass.text, year.text))
        }
        return sendIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
}