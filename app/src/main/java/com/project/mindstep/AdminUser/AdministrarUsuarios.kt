package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login
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
        val citasImagen: ImageView = findViewById(R.id.citas_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        registroImagen.setOnClickListener { navigateToRegistroActivity() }
        citasImagen.setOnClickListener { navigateToCitasActivity() }

        val roles = listOf("Paciente", "Medico", "Gestor de usuario", "Creador de Test")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        tipoDeCuenta.adapter = adaptador


        val perfilClick : Spinner = findViewById(R.id.perfil_clickable)

        val opcionesPerfil = listOf(" ","Cerrar Sesión")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesPerfil)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        perfilClick.adapter = adapter

        perfilClick.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>,
                selectedItemView: View,
                position: Int,
                id: Long
            ) {
                // Esto se ejecuta cuando se selecciona un elemento del Spinner
                val item = parentView.getItemAtPosition(position).toString()
                // Realiza acciones en función del elemento seleccionado
                perfilClickable(item)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Esto se ejecuta cuando no se selecciona ningún elemento
            }
        })
    }

    private fun perfilClickable(opcion : String){
        if(opcion == "Cerrar Sesión"){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finishAfterTransition()
        }
    }

    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesAdmin::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToRegistroActivity() {
        val intent = Intent(this, NuevoUsuario::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToCitasActivity() {
        val intent = Intent(this, AsignarCitas::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

}