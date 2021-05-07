package com.neergsw.ad340_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*First Button*/
        var button1: Button = findViewById(R.id.button1);

        //redirect activity from main activity to MovieActivity activity page
        button1.setOnClickListener {
            val intent = Intent(this, MovieList::class.java) //two arguments
            startActivity(intent) //This start an activity to pass the intent object to it. This will take us from main activity to second activity

        }

     /*Second Button*/
        var button2: Button = findViewById(R.id.button2);

        button2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java) //two arguments
            startActivity(intent) //From main activity to second activity
        }

     /*Third Button*/
        var button3: Button = findViewById(R.id.button3);

        button3.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java) //two arguments
            startActivity(intent) //From main activity to second activity
        }

     /*Fourth Button*/
        var button4: Button = findViewById(R.id.button4);

        button4.setOnClickListener {
            Toast.makeText(this, "Clicking Fourth Button", Toast.LENGTH_SHORT).show();
        }
    }
}