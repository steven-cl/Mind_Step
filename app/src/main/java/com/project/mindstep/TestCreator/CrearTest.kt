package com.project.mindstep.TestCreator

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

class CrearTest : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_test)


        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val administracionTest: ImageView = findViewById(R.id.administracionTest)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        administracionTest.setOnClickListener { navigateToAdministrarActivity() }

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
        val intent = Intent(this, AjustesTestCreator::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToAdministrarActivity() {
        val intent = Intent(this, AdministrarTest::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

}