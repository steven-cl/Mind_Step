package com.project.mindstep.Medico

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class Medicamentos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicamentos)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        testImagen.setOnClickListener { navigateToTestMedicoActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
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

}