package com.example.dragonx

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.dragonx.databinding.ActivityRocketDetailsBinding
import com.example.dragonx.models.Rocket
import java.net.URL

@Suppress("DEPRECATION")
class RocketDetails : AppCompatActivity() {
    private lateinit var binding: ActivityRocketDetailsBinding
    lateinit var rocket: Rocket
    var rockets = arrayListOf<Rocket>()
    var imageList = ArrayList<SlideModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRocketDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var position = intent.getIntExtra("rocket", 0)
        JsonRocketParser(this, this).execute(position)
    }

    @Suppress("DEPRECATION")
    inner class JsonRocketParser(
        @SuppressLint("StaticFieldLeak") var activity: Activity,
        var context: Context
    ) : AsyncTask<Int, Void, Void>() {

        private lateinit var jsonArray: JsonArray<JsonObject>

        override fun doInBackground(vararg p0: Int?): Void? {

            val rocketNumber = p0[0]
            val result = URL("https://api.spacexdata.com/v4/dragons").readText()
            val parser: Parser = Parser()
            val stringBuilder: StringBuilder = StringBuilder(result)
            jsonArray = parser.parse(stringBuilder) as JsonArray<JsonObject>
            rocket = Rocket()
            rocket.name = jsonArray.string("name")[rocketNumber!!]
            rocket.description = jsonArray.string("description")[rocketNumber]
            rocket.link = jsonArray.string("wikipedia")[rocketNumber]
            rocket.height =
                ("Diameter : meters : ${jsonArray.obj("diameter")[rocketNumber]?.double("meters")}," +
                        " feet : ${jsonArray.obj("diameter")[rocketNumber]?.int("feet").toString()}")
            rocket.mass =
                ("Dry mass : ${jsonArray.int("dry_mass_kg")[rocketNumber]} (${jsonArray.int("dry_mass_lb")[0]}) lb")

            rocket.year = jsonArray.string("first_flight")[rocketNumber]
            /*rocket.image = URL(images[p0])*/
            if (rocketNumber == 0) {
                imageList.add(
                    SlideModel(
                        "https://live.staticflickr.com/8578/16655995541_7817565ea9_k.jpg",
                        "Dragon1 pic.1"
                    )
                )
                imageList.add(
                    SlideModel(
                        "https://farm3.staticflickr.com/2815/32761844973_4b55b27d3c_b.jpg",
                        "Dragon1 pic.2"
                    )
                )
                imageList.add(
                    SlideModel(
                        "https://farm9.staticflickr.com/8618/16649075267_d18cbb4342_b.jpg",
                        "Dragon1 pic.3"
                    )
                )
            }else {
                imageList.add(
                    SlideModel(
                        "https://farm8.staticflickr.com/7647/16581815487_6d56cb32e1_b.jpg",
                        "Dragon2 pic.1"
                    )
                )
                imageList.add(
                    SlideModel(
                        "https://farm1.staticflickr.com/780/21119686299_c88f63e350_b.jpg",
                        "Dragon2 pic.2"
                    )
                )
                imageList.add(
                    SlideModel(
                        "https://farm9.staticflickr.com/8588/16661791299_a236e2f5dc_b.jpg",
                        "Dragon2 pic.3"
                    )
                )
            }
            rockets.add(rocket)
            return null
        }

        override fun onPostExecute(rockets: Void?) {
            super.onPostExecute(rockets)
            binding.rocketName.text = rocket.name
            binding.description.text = rocket.description
            binding.wikiLink.text = rocket.link
            binding.heightRocket.text = rocket.height
            binding.mass.text = rocket.mass
            binding.year.text = rocket.year
            binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}