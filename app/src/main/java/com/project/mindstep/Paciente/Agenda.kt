package com.project.mindstep.Paciente

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.mindstep.R
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Agenda : AppCompatActivity() {
    private lateinit var textViewUsuarios: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        calendarioImagen.setOnClickListener { navigateToCalendarioActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        testImagen.setOnClickListener { navigateToTestActivity() }

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



    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesPaciente::class.java)
        startActivity(intent)
    }

    private fun navigateToCalendarioActivity() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
    }

    private fun navigateToGraficaActivity() {
        val intent = Intent(this, Estado::class.java)
        startActivity(intent)
    }

    private fun navigateToTestActivity() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
    }
}