package com.project.mindstep.Paciente

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class Estado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estado)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)
        val testImagen: ImageView = findViewById(R.id.test_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        calendarioImagen.setOnClickListener { navigateToCalendarioActivity() }
        testImagen.setOnClickListener { navigateToTestActivity() }
    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesPaciente::class.java)
        startActivity(intent)
    }

    private fun navigateToCalendarioActivity() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
    }

    private fun navigateToTestActivity() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
    }
}