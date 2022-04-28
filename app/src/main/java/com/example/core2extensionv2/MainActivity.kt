package com.example.core2extensionv2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var globalID = ""
    var name = ""
    var location = ""
    var ratingbar = 3.14f
    var date = ""

    var carviewdata = mutableListOf<String>("HK Street View", "Hong kong", "3.64f", "30/11/18")
    var riverviewdata =
        mutableListOf<String>("Hong Kong River", "Sha Tin, Hong Kong", "2.12f", "29/12/18")
    var prisiondata =
        mutableListOf<String>("Abandon Prison Island", "Alcatraz island , USA", "1.40f", "09/12/18")
    var airportdata = mutableListOf<String>(
        "Hong Kong International Airport",
        "Chek lap kok Airport, HK",
        "4.5f",
        "30/12/18"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        show()
        val carview = findViewById<ImageView>(R.id.carview)
        carview.setOnClickListener {
            globalID = "HK Street"
            name = carviewdata.elementAt(0).toString()
            location = carviewdata.elementAt(1).toString()
            ratingbar = carviewdata.elementAt(2).toString().toFloat()
            date = carviewdata.elementAt(3).toString()
            proccess_page()
        }

        val riverView = findViewById<ImageView>(R.id.RiverView)
        riverView.setOnClickListener {
            globalID = "HK River"
            name = riverviewdata.elementAt(0).toString()
            location = riverviewdata.elementAt(1).toString()
            ratingbar = riverviewdata.elementAt(2).toString().toFloat()
            date = riverviewdata.elementAt(3).toString()
            proccess_page()
        }

        val prisonIsland = findViewById<ImageView>(R.id.PrisionIsland)
        prisonIsland.setOnClickListener {
            globalID = "Prison island"
            name = prisiondata.elementAt(0).toString()
            location = prisiondata.elementAt(1).toString()
            ratingbar = prisiondata.elementAt(2).toString().toFloat()
            date = prisiondata.elementAt(3).toString()
            proccess_page()
        }

        val airport = findViewById<ImageView>(R.id.HKAirport)
        airport.setOnClickListener {
            globalID = "HK airport"
            name = airportdata.elementAt(0).toString()
            location = airportdata.elementAt(1).toString()
            ratingbar = airportdata.elementAt(2).toString().toFloat()
            date = airportdata.elementAt(3).toString()
            proccess_page()
        }
    }

    /*private fun proccess_pages(){
        val imgdata = imgdata(globalID, name, location, ratingbar, date)
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("imgdata", imgdata)
        }

        startActivity(intent);
    }*/
    private fun proccess_page() {
        val imgdata = imgdata(globalID, name, location, ratingbar, date)
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("imgdata", imgdata)
        }

        startForResult.launch(intent);
    }

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    val data = result.data
                    val ImgData = data?.getParcelableExtra<imgdata>("returndata")
                    ImgData?.let {
                        val id = it.id
                        globalID = id
                        val imgName = it.name
                        val imglocation = it.location
                        val imgrating = it.rating
                        val imgdate = it.date
                        when (globalID) {
                            "HK Street" -> {
                                carviewdata.set(0, imgName)
                                carviewdata.set(1, imglocation)
                                carviewdata.set(2, imgrating.toString())
                                carviewdata.set(3, imgdate)
                                show()
                            }
                            "HK River" -> {
                                riverviewdata.set(0, imgName)
                                riverviewdata.set(1, imglocation)
                                riverviewdata.set(2, imgrating.toString())
                                riverviewdata.set(3, imgdate)
                                show()
                            }
                            "Prison island" -> {
                                prisiondata.set(0, imgName)
                                prisiondata.set(1, imglocation)
                                prisiondata.set(2, imgrating.toString())
                                prisiondata.set(3, imgdate)
                                show()
                            }
                            "HK airport" -> {

                                airportdata.set(0, imgName)
                                airportdata.set(1, imglocation)
                                airportdata.set(2, imgrating.toString())
                                airportdata.set(3, imgdate)
                                show()
                            }
                            else -> globalID = ""
                        }
                    }
                }
            }
        }

    private fun show() {
        val vStreetName = findViewById<TextView>(R.id.itemname1)
        val vRiverName = findViewById<TextView>(R.id.itemname2)
        val vPrision = findViewById<TextView>(R.id.itemname3)
        val vAirport = findViewById<TextView>(R.id.itemname4)
        val vratingStreet = findViewById<TextView>(R.id.rating1)
        val vratingRiver = findViewById<TextView>(R.id.rating2)
        val vPrisionRating = findViewById<TextView>(R.id.rating3)
        val vAirportRating = findViewById<TextView>(R.id.rating4)


        vRiverName.text = riverviewdata.elementAt(0).toString()
        vratingRiver.text = riverviewdata.elementAt(2).toString().toFloat().toString() + "/5"
        vStreetName.text = carviewdata.elementAt(0).toString()
        vratingStreet.text = carviewdata.elementAt(2).toString().toFloat().toString() + "/5"
        vPrision.text = prisiondata.elementAt(0).toString()
        vPrisionRating.text = prisiondata.elementAt(2).toString().toFloat().toString() + "/5"
        vAirport.text = airportdata.elementAt(0).toString()
        vAirportRating.text = airportdata.elementAt(2).toString().toFloat().toString() + "/5"

    }
}