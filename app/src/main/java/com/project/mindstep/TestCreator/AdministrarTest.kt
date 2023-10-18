package com.project.mindstep.TestCreator

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class AdministrarTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_test)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToCrearTestActivity() }
    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesTestCreator::class.java)
        startActivity(intent)
    }

    private fun navigateToCrearTestActivity() {
        val intent = Intent(this, CrearTest::class.java)
        startActivity(intent)
    }

}