package com.project.mindstep.Paciente

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mindstep.R
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class Agenda : AppCompatActivity() {
    private lateinit var textViewUsuarios: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        textViewUsuarios = findViewById(R.id.textViewUsuarios)

        // Check if Intent contains user data
        val userData = intent.extras
        if (userData != null) {
            // User data is available, you can extract and display it
            displayUserData(userData)
        } else {
            // If no user data, show a message and finish the activity
            Toast.makeText(this, "Error: Login not successful", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun displayUserData(userData: Bundle) {
        //val numeroExpediente = userData.getString("NumeroExpediente", "")
        //val cedula = userData.getString("Cedula", "")
        val nombres = userData.getString("Nombres","")
        // Extract other user data here...

        // Display the user data in the TextView
        // Add other user data to the string...S

        Log.d("Agenda", "User Data: $nombres")
        textViewUsuarios.text = nombres
    }

}