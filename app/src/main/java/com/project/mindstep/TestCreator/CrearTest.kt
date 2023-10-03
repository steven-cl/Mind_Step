package com.project.mindstep.TestCreator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.project.mindstep.R

class CrearTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_test)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToCrearTestActivity() }
        calendarioImagen.setOnClickListener { navigateToAdministrarActivity() }
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