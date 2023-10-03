package com.project.mindstep.AdminUser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.project.mindstep.R

class AjustesAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_admin)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val registroImagen: ImageView = findViewById(R.id.regitro_imagen)
        val administradorImagen: ImageView = findViewById(R.id.administrador_imagen)
        val citasImagen: ImageView = findViewById(R.id.citas_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        registroImagen.setOnClickListener { navigateToRegistroActivity() }
        administradorImagen.setOnClickListener { navigateToAdministradorActivity() }
        citasImagen.setOnClickListener { navigateToCitasActivity() }

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

    private fun navigateToCitasActivity() {
        val intent = Intent(this, AsignarCitas::class.java)
        startActivity(intent)
    }

}