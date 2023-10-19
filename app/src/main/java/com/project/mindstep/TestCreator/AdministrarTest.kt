package com.project.mindstep.TestCreator

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


class AdministrarTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_test)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val graficaImagen: ImageView = findViewById(R.id.grafica_imagen)
        val perfilClick : Spinner = findViewById(R.id.perfil_clickable)

        val opcionesPerfil = listOf(" ", "Cerrar Sesión", "Perfil")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesPerfil)
        perfilClick.adapter = adapter

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        graficaImagen.setOnClickListener { navigateToCrearTestActivity() }

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
       /*if (opcion == "Perfil"){

        }else */if(opcion == "Cerrar Sesión"){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
            onDestroy()
        }
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
