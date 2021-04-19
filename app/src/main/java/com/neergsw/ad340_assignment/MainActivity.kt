package com.neergsw.ad340_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     /*First Button*/
        var button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(this, "Clicking First Button", Toast.LENGTH_SHORT).show();
        }

     /*Second Button*/
        var button2: Button = findViewById(R.id.button2);

        button2.setOnClickListener {
            Toast.makeText(this, "Clicking Second Button", Toast.LENGTH_SHORT).show();
        }

     /*Third Button*/
        var button3: Button = findViewById(R.id.button3);

        button3.setOnClickListener {
            Toast.makeText(this, "Clicking Third Button", Toast.LENGTH_SHORT).show();
        }

     /*Fourth Button*/
        var button4: Button = findViewById(R.id.button4);

        button4.setOnClickListener {
            Toast.makeText(this, "Clicking Fourth Button", Toast.LENGTH_SHORT).show();
        }
    }
}