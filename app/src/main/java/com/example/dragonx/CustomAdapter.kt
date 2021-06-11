package com.example.dragonx

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import java.net.URL



@Suppress("NAME_SHADOWING")
class CustomAdapter(private val context: Context, private val rockets: List<Rocket>) : BaseAdapter() {
    private val inflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return rockets.size
    }

    override fun getItem(p0: Int): Any {
        return rockets[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val convertView = inflater.inflate(R.layout.homelayout, p2, false)
        val name = convertView?.findViewById(R.id.name) as TextView
        /*val description = convertView?.findViewById(R.id.descriptionRocket) as TextView
        val linkWiki = convertView?.findViewById(R.id.linkWiki) as TextView
        val height = convertView?.findViewById(R.id.height) as TextView
        val massRocket = convertView?.findViewById(R.id.massRocket) as TextView
        val yearRocket = convertView?.findViewById(R.id.yearRocket) as TextView*/
        val image = convertView?.findViewById<ImageView>(R.id.imageView2)

        val rocket: Rocket = rockets.get(p0)

        name.text = rocket.name
        /*description.text = rocket.description
        linkWiki.text = rocket.link
        height.text = rocket.height
        massRocket.text = rocket.mass
        yearRocket.text = rocket.year*/

        val imageurl = URL(rocket.image)
        Glide.with(context).load(imageurl).into(image)

        return convertView
    }
    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val inflater =
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.homelayout, null)
        }
        val name = convertView?.findViewById<TextView>(R.id.name)
        val description = convertView?.findViewById<TextView>(R.id.descriptionRocket)
        val linkWiki = convertView?.findViewById<TextView>(R.id.linkWiki)
        val height = convertView?.findViewById<TextView>(R.id.height)
        val massRocket = convertView?.findViewById<TextView>(R.id.massRocket)
        val yearRocket = convertView?.findViewById<TextView>(R.id.yearRocket)
        val image = convertView?.findViewById<ImageView>(R.id.imageView2)

        val rocket: Rocket = rockets.get(position)
        if (name != null) {
            name.text = rocket.name
        }
        if (description != null) {
            description.text = rocket.description
        }
        if (linkWiki != null) {
            linkWiki.text = rocket.link
        }
        if (height != null) {
            height.text = rocket.height
        }
        if (massRocket != null) {
            massRocket.text = rocket.mass
        }
        if (yearRocket != null) {
            yearRocket.text = rocket.year
        }
        try {
            val imageurl = URL(rocket.image)
            if (image != null) {
                Glide.with(context).load(imageurl).into(image)
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return super.getView(position, convertView, parent)
    }*/
}