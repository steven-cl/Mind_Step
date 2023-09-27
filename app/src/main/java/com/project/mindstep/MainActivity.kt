package com.project.mindstep

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Paciente.Agenda

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        handler.postDelayed({
            reenvio()
        },500L)
    }

    //Esta funcion comprueba si la sesion del usuario esta iniciada, en ese caso, se dirige al area que le correspode
    //en caso contrario se dirige al login
    fun reenvio(){
        //por ahora para ver los resultados visuales me redirigira automaticamente a la agenda del paciente
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
    }
}