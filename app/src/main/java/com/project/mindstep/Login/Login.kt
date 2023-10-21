package com.project.mindstep.Login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.AdminUser.NuevoUsuario
import com.project.mindstep.FetchUsuariosAsyncTask
import com.project.mindstep.Medico.Resultados
import com.project.mindstep.Paciente.Agenda
import com.project.mindstep.R
import com.project.mindstep.TestCreator.CrearTest

class Login : AppCompatActivity(), FetchUsuariosAsyncTask.TaskListener {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextPassword = findViewById<EditText>(R.id.ETPassword)
        val imageViewPasswordVisibility = findViewById<ImageView>(R.id.Ojo)

        var isPasswordVisible = false

        imageViewPasswordVisibility.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Mostrar la contraseña
                editTextPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // Ocultar la contraseña
                editTextPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }

            // Mueve el cursor al final del texto para que sea visible
            editTextPassword.setSelection(editTextPassword.text.length)
        }

        emailEditText = findViewById(R.id.ETEmailAddress)
        passwordEditText = findViewById(R.id.ETPassword)
        loginButton = findViewById(R.id.iniciar_sesion)

        // Set up a click listener for the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform basic validation
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Initiate the login process
                val fetchUsuariosAsyncTask = FetchUsuariosAsyncTask(this, this)
                @Suppress("DEPRECATION")
                fetchUsuariosAsyncTask.execute(email, password)
            }
            else if (email.isNotEmpty() && !password.isNotEmpty()) {
                Toast.makeText(this, "Ingrese Una Contraseña", Toast.LENGTH_SHORT).show()
            }
            else if (!email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Ingrese Un Usuario", Toast.LENGTH_SHORT).show()
            }
            else {
                // Show a toast message for empty fields
                Toast.makeText(this, "Ingrese Usuario y Contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onTaskCompleted(result: String) {
        // Handle the result of the login process
        if (result == "Authentication failed") {
            // Show a toast message for failed authentication
            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
        }

        //navegacion a la activity indicada
        if(result == "4"){
            // Authentication successful, navigate to the Agenda activity
            startActivity(Intent(this, Agenda::class.java))
            finishAfterTransition()
        }else if (result == "2"){
            startActivity(Intent(this, Resultados::class.java))
            finishAfterTransition()
        }else if (result == "1"){
            startActivity(Intent(this, NuevoUsuario::class.java))
            finishAfterTransition()
        }else if (result == "3") {
            startActivity(Intent(this, CrearTest::class.java))
            finishAfterTransition()
        }
        else{
            Toast.makeText(this, "Usuario y Contraseña Incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}