package com.project.mindstep.Medico

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.project.mindstep.Paciente.Agenda
import com.project.mindstep.Paciente.AjustesPaciente
import com.project.mindstep.Paciente.Estado
import com.project.mindstep.Paciente.Test
import com.project.mindstep.R

class Resultados : AppCompatActivity() {

    private lateinit var textViewUsuarios: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val pastillaImagen: ImageView = findViewById(R.id.pastilla_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        testImagen.setOnClickListener { navigateToTestMedicoActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        pastillaImagen.setOnClickListener { navigateToMedicamentosActivity() }

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
        //val tipoUser = userData.getString("TipoUser", "")

        // Display the user data in the TextView
        // Add other user data to the string...S

        Log.d("Agenda", "User Data: $nombres")
        textViewUsuarios.text = nombres
    }


    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesMedico::class.java)
        startActivity(intent)
    }

    private fun navigateToTestMedicoActivity() {
        val intent = Intent(this, TestMedico::class.java)
        startActivity(intent)
    }

    private fun navigateToGraficaActivity() {
        val intent = Intent(this, Resultados::class.java)
        startActivity(intent)
    }

    private fun navigateToMedicamentosActivity() {
        val intent = Intent(this, Medicamentos::class.java)
        startActivity(intent)
    }




}






