package com.example.dragonx.presentation.rocketDetails

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.dragonx.R
import com.example.dragonx.model.util.Constants.Companion.IMAGE_SLIDER_POSITION


class RocketDetailsFragment : Fragment() {
    private val args: RocketDetailsFragmentArgs by navArgs()

    private val rocketName: TextView by lazy { requireView().findViewById(R.id.rocketName) }
    private val description: TextView by lazy { requireView().findViewById(R.id.description) }
    private val wikiLink: TextView by lazy { requireView().findViewById(R.id.wikiLink) }
    private val heightRocket: TextView by lazy { requireView().findViewById(R.id.heightRocket) }
    private val mass: TextView by lazy { requireView().findViewById(R.id.mass) }
    private val year: TextView by lazy { requireView().findViewById(R.id.year) }
    private val beforeButton: ImageButton by lazy { requireView().findViewById(R.id.before_button) }
    private val nextButton: ImageButton by lazy { requireView().findViewById(R.id.next_button) }
    private lateinit var adapter: ImageSliderAdapter
    private var position = 0
    private lateinit var imageSlider: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = savedInstanceState?.getInt(IMAGE_SLIDER_POSITION) ?: 0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rocket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageSlider = view.findViewById<ViewPager2>(R.id.viewPager)
        imageSlider.isUserInputEnabled = false

        setHasOptionsMenu(true)
        adapter = ImageSliderAdapter()
        val rocket = args.rocket
        rocketName.text = rocket.name
        description.text = rocket.description
        wikiLink.text = rocket.wikipedia
        heightRocket.text = getString(
            R.string.rocket_diameter, rocket.diameter.meters, rocket.diameter.feet)
        mass.text = getString(R.string.rocket_mass, rocket.massKg, rocket.massLb)
        year.text = rocket.firstFlight
        adapter.submitList(rocket.flickrImages)
        imageSlider.adapter = adapter

        if (position == 0) {
            beforeButton.visibility = View.GONE
        } else if (position == imageSlider.adapter?.itemCount?.minus(1)!!) {
            nextButton.visibility = View.GONE
        }

        beforeButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                position = imageSlider.currentItem
                nextButton.visibility = View.VISIBLE
                if (position > 0) {
                    position--
                    imageSlider.currentItem = position
                    if (position == 0) {
                        beforeButton.visibility = View.GONE
                    }
                }
            }
        })

        nextButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                position = imageSlider.currentItem
                beforeButton.visibility = View.VISIBLE
                if (position < imageSlider.adapter?.itemCount?.minus(1)!!) {
                    position++
                    imageSlider.currentItem = position
                    if (position == imageSlider.adapter?.itemCount?.minus(1)!!) {
                        nextButton.visibility = View.GONE
                    }
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(IMAGE_SLIDER_POSITION, position)
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