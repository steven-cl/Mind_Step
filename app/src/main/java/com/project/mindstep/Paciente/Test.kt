package com.project.mindstep.Paciente

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        calendarioImagen.setOnClickListener { navigateToCalendarioActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
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

}