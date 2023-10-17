package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner

import com.project.mindstep.R

class AdministrarUsuarios : AppCompatActivity() {

    private lateinit var tipoDeCuenta : Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_usuarios)

        tipoDeCuenta = findViewById(R.id.espTipo)
        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val registroImagen: ImageView = findViewById(R.id.regitro_imagen)
        val administradorImagen: ImageView = findViewById(R.id.administrador_imagen)
        val citasImagen: ImageView = findViewById(R.id.citas_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        registroImagen.setOnClickListener { navigateToRegistroActivity() }
        administradorImagen.setOnClickListener { navigateToAdministradorActivity() }
        citasImagen.setOnClickListener { navigateToCitasActivity() }

        val roles = listOf("Paciente", "Medico", "Gestor de usuario", "Creador de Test")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        tipoDeCuenta.adapter = adaptador

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