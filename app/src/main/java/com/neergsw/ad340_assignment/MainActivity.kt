package com.neergsw.ad340_assignment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       //init share pref (either getDefaultSharedPreferences or getSharedPreferences)
        // val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val pref = getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE)

        pref.apply {
            val userName = getString("NAME", "")
            val email = getString("EMAIL", "")
            val password = getString("PASSWORD", "")
            edit_name.setText(userName)
            edit_email.setText(email)
            edit_password.setText(password)

        }

        /*First Button*/
        var button1: Button = findViewById(R.id.button1);

        button1.setOnClickListener {
            val intent = Intent(this, MovieList::class.java)
            startActivity(intent)
        }

        /*Second Button*/
        var button2: Button = findViewById(R.id.button2);

        button2.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)

        }

        /*Third Button*/
        var button3: Button = findViewById(R.id.button3);

        button3.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        /*Fourth Button*/
        var button4: Button = findViewById(R.id.button4);

        button4.setOnClickListener {
            Toast.makeText(this, "Clicking Fourth Button", Toast.LENGTH_SHORT).show();
        }

      //Call out for Login Button
        button_login.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        Log.d("FIREBASE", "signIn")

    // 1 - validate name, email, and password entries
        val userName = edit_name.text.toString().trim()
        val email = edit_email.text.toString().trim()
        val password = edit_password.text.toString()

        if(email.isEmpty()){
            edit_email.error = "Field can't be empty"
            edit_email.requestFocus()
            return
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edit_email.error = "Invalid email format"
            edit_email.requestFocus()
            return
        } else if(password.isEmpty()){
            edit_password.error = "Password is required"
            edit_password.requestFocus()
            return
        }

    // 2 - save valid entries to shared preferences
        // (either getDefaultSharedPreferences or getSharedPreferences)
        // val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val pref = getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE)
        val editor = pref.edit()

        editor
            .putString("NAME", userName)
            .putString("EMAIL", email)
            .putString("PASSWORD", password)
            .apply()

    // 3 - sign into Firebase
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful)
                if (task.isSuccessful) { //That a user has been logged in
                    // update profile (redirect to their profile hence FirebaseActivity)
                    val user = FirebaseAuth.getInstance().currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(userName)
                        .build()

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("FIREBASE", "User profile updated.")
                                // Go to FirebaseActivity
                                startActivity( Intent(this, FirebaseActivity::class.java))
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("FIREBASE", "sign-in failed")
                    Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
