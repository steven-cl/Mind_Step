package com.project.mindstep.Paciente

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class Agenda : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        testImagen.setOnClickListener { navigateToTestActivity() }
    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesPaciente::class.java)
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