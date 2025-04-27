package com.example.aisafetyincidenttracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signupLink)
        val login = findViewById<TextView>(R.id.loginButton)

        // Fetch username and password when the login button is clicked
        login.setOnClickListener {
            val username = findViewById<TextView>(R.id.usernameEditText).text.toString()
            val loginpassword = findViewById<TextView>(R.id.passwordEditText).text.toString()

            // Check if username and password are provided
            if (username.isNotEmpty() && loginpassword.isNotEmpty()) {
                val sharedPref = getSharedPreferences("My Details", Context.MODE_PRIVATE)
                val name = sharedPref.getString("name", "")
                val password = sharedPref.getString("password", "")

                if (name.isNullOrEmpty() || password.isNullOrEmpty()) {
                    Toast.makeText(this, "Please sign up first", Toast.LENGTH_SHORT).show()
                    navigateToSignUp() // Redirect to SignUp Fragment
                    return@setOnClickListener
                }


                // Check if the credentials match
                if (name == username && loginpassword == password) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    // Clear the input fields if login fails
                    findViewById<TextView>(R.id.usernameEditText).text = ""
                    findViewById<TextView>(R.id.passwordEditText).text = ""
                    Toast.makeText(this, "Invalid Login Details", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter login details", Toast.LENGTH_SHORT).show()
            }
        }

        signup.setOnClickListener {
            navigateToSignUp() // Navigate to SignUp Fragment
        }
    }

    // Function to navigate to SignUp Fragment
    private fun navigateToSignUp() {
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

}