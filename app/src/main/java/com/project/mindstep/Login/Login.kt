package com.project.mindstep.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mindstep.R
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.mindstep.FetchUsuariosAsyncTask
import com.project.mindstep.Paciente.Agenda

class Login : AppCompatActivity(), FetchUsuariosAsyncTask.TaskListener {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.ETEmailAddress)
        passwordEditText = findViewById(R.id.ETPassword)
        loginButton = findViewById(R.id.iniciar_sesion)

        // Set up a click listener for the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform basic validation
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Initiate the login process
                val fetchUsuariosAsyncTask = FetchUsuariosAsyncTask(this, this)
                @Suppress("DEPRECATION")
                fetchUsuariosAsyncTask.execute(email, password)
            } else {
                // Show a toast message for empty fields
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onTaskCompleted(result: String) {
        // Handle the result of the login process
        if (result == "Authentication failed") {
            // Show a toast message for failed authentication
            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
        } else {
            // Authentication successful, navigate to the Agenda activity
            startActivity(Intent(this, Agenda::class.java))
            finish() // Optional: finish the current activity to prevent going back to the login screen
        }
    }
}