package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Space
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.R


class NuevoUsuario : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val roles = listOf("Selecciona un rol", "Paciente", "Medico", "Gestor de usuario", "Creador de Test")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        val spinner = findViewById<Spinner>(R.id.RolesUsuarios)
        spinner.adapter = adapter
    }
}