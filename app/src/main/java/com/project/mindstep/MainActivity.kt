package com.project.mindstep

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hacer una pequeña pausa para tener una imagen de inicio antes de iniciar el programa principal
        @Suppress("DEPRECATION") val handler = Handler()
        handler.postDelayed({
            reenvio()
        },1000L)
    }

    //Esta funcion comprueba si la sesion del usuario esta iniciada, en ese caso, se dirige al area que le correspode
    //en caso contrario se dirige al login
    private fun reenvio(){
        //por ahora para ver los resultados visuales me redirigira automaticamente a la agenda del paciente
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}