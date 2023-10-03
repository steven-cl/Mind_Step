package com.project.mindstep.Paciente

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mindstep.R
import android.util.Log
import android.view.View
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


            finish()
        }
    }

    private fun displayUserData(userData: Bundle) {
        //val numeroExpediente = userData.getString("NumeroExpediente", "")
        //val cedula = userData.getString("Cedula", "")
        val nombres = userData.getString("Nombres","")
        val tipoUser = userData.getString("TipoUser", "")

        // Display the user data in the TextView
        // Add other user data to the string...S

        Log.d("Agenda", "User Data: $nombres")
        textViewUsuarios.text = nombres
    }

    fun onViewClick(view: View) {
        when (view.id) {
            R.id.view4 -> navigateToActivity1()
            R.id.view5 -> navigateToActivity2()
            R.id.view6 -> navigateToActivity3()
            R.id.view -> navigateToActivity4()
        }
    }

    private fun navigateToActivity1() {
        val intent = Intent(this, Estado::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity2() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity3() {
        val intent = Intent(this, AjustesPaciente::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity4() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
    }


}