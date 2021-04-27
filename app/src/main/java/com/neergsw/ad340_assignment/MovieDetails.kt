package com.neergsw.ad340_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.neergsw.recltest.MovieArray

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val data = intent.getParcelableExtra<MovieArray>(MovieList.INTENT_PARCELABLE)

        val imgSrc = findViewById<ImageView>(R.id.movie_image)
        val theTitlte = findViewById<TextView>(R.id.movie_title)
        val theYear = findViewById<TextView>(R.id.movie_year)
        val theDesc = findViewById<TextView>(R.id.movie_desc)
        val theDirector = findViewById<TextView>(R.id.movie_director)

        if (data != null) {
            imgSrc.setImageResource(data.imageSrc)
            theTitlte.text = data.title
            theYear.text = data.year
            theDesc.text = data.description
            theDirector.text = data.director
        }


//backButton

        val backButton = supportActionBar

        //!! is  non-null type
        backButton!!.title = "Movie Details"

        //Enable back button so we need to call on setDisplayHomeAsUpEnabled
        //To have action, go to AndroidManifest to add "parentActivityName" to .MovieActivity before closing tag then call for previous activity which is MainActivity
        backButton.setDisplayHomeAsUpEnabled(true)

    }
}