package com.project.mindstep.Paciente

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
        val intent = Intent(this, AjustesPaciente::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToCalendarioActivity() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToTestActivity() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
        finishAfterTransition()
    }
}