package com.project.mindstep.Medico

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class TestMedico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_medico)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val pastillaImagen: ImageView = findViewById(R.id.pastilla_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        pastillaImagen.setOnClickListener { navigateToMedicamentosActivity() }

    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesMedico::class.java)
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