package com.project.mindstep.Paciente

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login
import com.project.mindstep.R

class AjustesPaciente : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_paciente)

        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)

        calendarioImagen.setOnClickListener { navigateToCalendarioActivity() }
        graficaImagen.setOnClickListener { navigateToGraficaActivity() }
        testImagen.setOnClickListener { navigateToTestActivity() }

        val btnCerrarSesion : Button = findViewById(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener{cerrarSesion()}
    }

    private fun cerrarSesion(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToCalendarioActivity() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToGraficaActivity() {
        val intent = Intent(this, Estado::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToTestActivity() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
        finishAfterTransition()
    }
}