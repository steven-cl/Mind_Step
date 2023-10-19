package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R

class AsignarCitas : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_citas)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val registroImagen: ImageView = findViewById(R.id.regitro_imagen)
        val administradorImagen: ImageView = findViewById(R.id.administrador_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        registroImagen.setOnClickListener { navigateToRegistroActivity() }
        administradorImagen.setOnClickListener { navigateToAdministradorActivity() }
    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesAdmin::class.java)
        startActivity(intent)
    }

    private fun navigateToRegistroActivity() {
        val intent = Intent(this, NuevoUsuario::class.java)
        startActivity(intent)
    }

    private fun navigateToAdministradorActivity() {
        val intent = Intent(this, AdministrarUsuarios::class.java)
        startActivity(intent)
    }

}