package com.example.aisafetyincidenttracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupButton = findViewById<TextView>(R.id.signupButton)
        signupButton.setOnClickListener {
            val name = findViewById<TextView>(R.id.usernameEditText)
            val email = findViewById<TextView>(R.id.emailEditText)
            val password = findViewById<TextView>(R.id.passwordEditText)

            val userName = name.text.toString().trim()
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                // Show error
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save details and move to MainActivity
                val sharedPref = getSharedPreferences("My Details", Context.MODE_PRIVATE)
                sharedPref.edit {
                    putString("name", userName)
                    putString("email", userEmail)
                    putString("password", userPassword)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}
