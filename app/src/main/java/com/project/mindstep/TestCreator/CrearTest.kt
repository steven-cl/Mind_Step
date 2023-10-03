package com.project.mindstep.TestCreator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.project.mindstep.Medico.AjustesMedico
import com.project.mindstep.Medico.Medicamentos
import com.project.mindstep.Medico.Resultados
import com.project.mindstep.Medico.TestMedico
import com.project.mindstep.R

class CrearTest : AppCompatActivity() {

    private lateinit var textViewUsuarios: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_test)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToCrearTestActivity() }
        calendarioImagen.setOnClickListener { navigateToAdministrarActivity() }

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
        val intent = Intent(this, AjustesTestCreator::class.java)
        startActivity(intent)
    }

    private fun navigateToCrearTestActivity() {
        val intent = Intent(this, CrearTest::class.java)
        startActivity(intent)
    }

    private fun navigateToAdministrarActivity() {
        val intent = Intent(this, AdministrarTest::class.java)
        startActivity(intent)
    }

}