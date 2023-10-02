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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val buttonDropdown = findViewById<Button>(R.id.btnSelecionarFecha)
        val calendarView = findViewById<CalendarView>(R.id.calendarioFechaNac)
        val space = findViewById<Space>(R.id.spaceCalendario)

        // Oculta inicialmente el calendario
        calendarView.visibility = View.GONE
        space.visibility = View.GONE

        val roles = listOf("Selecciona un rol", "Paciente", "Medico", "Gestor de usuario", "Creador de Test")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        val spinner = findViewById<Spinner>(R.id.RolesUsuarios)
        spinner.adapter = adapter

        buttonDropdown.setOnClickListener {
            // Cambia la visibilidad del calendario al hacer clic en el botón
            if(calendarView.visibility == View.GONE){
                calendarView.visibility = View.VISIBLE
                space.visibility = View.VISIBLE
            }else{
                calendarView.visibility = View.GONE
                space.visibility = View.GONE
            }

        }

        // Configura el CalendarView
        //calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            //val selectedDate = "$dayOfMonth/${month + 1}/$year"
            // Haz algo con la fecha seleccionada, como mostrarla en un TextView o guardarla en una variable
        //}
    }
}