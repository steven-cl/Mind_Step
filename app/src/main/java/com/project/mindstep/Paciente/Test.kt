package com.project.mindstep.Paciente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.mindstep.R

class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    fun onViewClick(view: View) {
        when (view.id) {
            R.id.view4 -> navigateToActivity1()
            R.id.view5 -> navigateToActivity2()
            R.id.view6 -> navigateToActivity3()
            R.id.view -> navigateToActivity4()
        }
    }

    private fun navigateToActivity1() {
        val intent = Intent(this, Estado::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity2() {
        val intent = Intent(this, Test::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity3() {
        val intent = Intent(this, AjustesPaciente::class.java)
        startActivity(intent)
    }

    private fun navigateToActivity4() {
        val intent = Intent(this, Agenda::class.java)
        startActivity(intent)
    }
}