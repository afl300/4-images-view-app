package com.example.core2extensionv2

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class DetailActivity : AppCompatActivity() {
    var GlobalID = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val ImgData = intent.getParcelableExtra<imgdata>("imgdata")

        ImgData?.let {
            val vName = findViewById<TextInputEditText>(R.id.namevalue)
            val Name = it.name
            vName.setText(Name)
            val id = it.id
            val image = findViewById<ImageView>(R.id.outputimage)
            if (id == "HK Street") {
                //https://stackoverflow.com/questions/5089300/how-can-i-change-the-image-of-an-imageview
                image.setImageResource(R.drawable.img_2)
                image.setTag("HK Street")
                GlobalID = "HK Street"
            }
            if (id == "HK River") {
                image.setImageResource(R.drawable.img_3)
                image.setTag("HK river")
                GlobalID = "HK River"
            } else if (id == "Prison island") {
                image.setImageResource(R.drawable.img_4)
                image.setTag("Prison island")
                GlobalID = "Prison island"
            } else if (id == "HK airport") {
                image.setImageResource(R.drawable.img_5)
                image.setTag("HK airport")
                GlobalID = "HK airport"
            }
            val vDate = findViewById<TextInputEditText>(R.id.datevalue)
            val datedata = it.date
            vDate.setText(datedata)

            val vlocation = findViewById<TextInputEditText>(R.id.locationvalue)
            val grabbedlocation = it.location
            //https://discuss.kotlinlang.org/t/type-mismatch-required-editable-found-string/21656/2
            vlocation.setText(grabbedlocation)

            val rating_bar = findViewById<RatingBar>(R.id.ratingBar)
            rating_bar.rating = it.rating


        }

    }

    override fun onBackPressed() {
        //gather data
        val date = findViewById<TextInputEditText>(R.id.datevalue)
        val name = findViewById<TextInputEditText>(R.id.namevalue)
        val location = findViewById<TextInputEditText>(R.id.locationvalue)
        val ratingbar = findViewById<RatingBar>(R.id.ratingBar)
        val id = GlobalID
        var validate = true

        var nameCheck = Regex("[a-zA-Z ]{1,50}\$")//https://regex101.com/
        var locationCheck = Regex("[a-zA-Z, ]{1,50}\$")
        if (!validateDate(date.text.toString())) {
            date.setError("Value must be set as DD/MM/YY")
            validate = false;
        }
        if (!nameCheck.matches(name.text.toString()))//https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/matches.html
        {
            name.setError("Value must be under 50 characters and is alphabet")
            validate = false;
        }
        if (!locationCheck.matches(location.text.toString())) {
            location.setError("Value must be under 50 characters and is alphabet")
            validate = false
        }
        if (validate) {
            var data = imgdata(
                id,
                name.text.toString(),
                location.text.toString(),
                ratingbar.rating,
                date.text.toString()
            )
            val i = intent.apply {
                putExtra("returndata", data)
            }
            //https://developer.android.com/guide/topics/ui/notifiers/toasts
            val text = "Updated data"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

            setResult(Activity.RESULT_OK, i)
            super.onBackPressed()
        }
    }

    private fun validateDate(date: String): Boolean {//https://stackoverflow.com/questions/17416595/date-validation-in-android

        val regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"
        val matcher = Pattern.compile(regex).matcher(date)
        if (matcher.matches()) {
            matcher.reset()
            if (matcher.find()) {
                val dateDetails = date.split("/")
                val day: String = dateDetails[0]
                val month: String = dateDetails[1]
                val year: String = dateDetails[2]
                if (month.toInt() <= 12) {
                    if (day.toInt() <= 31) {
                        return true
                    }
                }
            }
        } else {
            return false
        }
        return false

    }//stackoverflow.com/questions/17416595/date-validation-in-android


}