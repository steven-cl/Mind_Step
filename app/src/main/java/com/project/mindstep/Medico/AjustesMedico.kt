package com.project.mindstep.Medico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login
import com.project.mindstep.R

class AjustesMedico : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_medico)

        val testImagen: ImageView = findViewById(R.id.test_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val pastillaImagen: ImageView = findViewById(R.id.pastilla_imagen)

        testImagen.setOnClickListener { navigateToTestMedicoActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        pastillaImagen.setOnClickListener { navigateToMedicamentosActivity() }

        val btnCerrarSesion : Button = findViewById(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener{cerrarSesion()}
    }

    private fun cerrarSesion(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToTestMedicoActivity() {
        val intent = Intent(this, TestMedico::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToGraficaActivity() {
        val intent = Intent(this, Resultados::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToMedicamentosActivity() {
        val intent = Intent(this, Medicamentos::class.java)
        startActivity(intent)
        finishAfterTransition()
    }
}