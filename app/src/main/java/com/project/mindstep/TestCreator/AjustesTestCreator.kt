package com.project.mindstep.TestCreator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login
import com.project.mindstep.R

class AjustesTestCreator : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_test_creator)

        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val calendarioImagen: ImageView = findViewById(R.id.calendario_imagen)

        graficaImagen.setOnClickListener { navigateToCrearTestActivity() }
        calendarioImagen.setOnClickListener { navigateToAdministrarActivity() }

        val btnCerrarSesion : Button = findViewById(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener{cerrarSesion()}
    }

    private fun cerrarSesion(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToCrearTestActivity() {
        val intent = Intent(this, CrearTest::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToAdministrarActivity() {
        val intent = Intent(this, AdministrarTest::class.java)
        startActivity(intent)
        finishAfterTransition()
    }
}