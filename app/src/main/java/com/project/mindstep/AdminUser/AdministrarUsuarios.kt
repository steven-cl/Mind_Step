package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.mindstep.Login.Login
import com.project.mindstep.R

class AdministrarUsuarios : AppCompatActivity() {

    private lateinit var tipoDeCuenta : Spinner
    private var usuarioEncontrado: Usuario? = null

    private lateinit var editText: EditText

    private lateinit var etCedula: EditText
    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etFechaNac: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasenia: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar_usuarios)

        editText = findViewById(R.id.editText)

        etCedula = findViewById(R.id.etCedula)
        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etFechaNac = findViewById(R.id.etFechaNac)
        etUsuario = findViewById(R.id.etUsuario)
        etCorreo = findViewById(R.id.etCorreo)
        etContrasenia = findViewById(R.id.etContraseña)

        // Find the EditText for searching by cedula
        val editText = findViewById<EditText>(R.id.editText)

        // Allow letters, numbers, and "-"
        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                val c = source[i]
                if (!Character.isLetterOrDigit(c) && c != '-') {
                    return@InputFilter ""
                }
            }
            null
        }
        editText.filters = arrayOf(inputFilter)

        val btnBuscar = findViewById<Button>(R.id.btnBuscar)

        btnBuscar.setOnClickListener {
            val cedula = editText.text.toString()

            val buscarUsuarioTask = BuscarUsuarioAsyncTask(object : BuscarUsuarioAsyncTask.BuscarUsuarioListener {
                override fun onUsuarioEncontrado(usuario: Usuario?) {
                    if (usuario != null) {
                        // Al encontrar un usuario, asigna el valor a usuarioEncontrado
                        usuarioEncontrado = usuario
                        // Llama a la función para mostrar los datos del usuario en los EditText
                        mostrarDatosDelUsuarioEnEditText()
                    } else {
                        // Usuario no encontrado, muestra un mensaje de error
                        Toast.makeText(this@AdministrarUsuarios, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(mensajeError: String) {
                    // Manejar errores de conexión o consulta
                    Toast.makeText(this@AdministrarUsuarios, "Error: $mensajeError", Toast.LENGTH_SHORT).show()
                }
            })

            buscarUsuarioTask.execute(cedula)
        }



        val btnModificar = findViewById<Button>(R.id.btnModificar)

        btnModificar.setOnClickListener {
            // Prepare the updated user data and create a ModificarUsuarioAsyncTask
            val selectedRolePosition = tipoDeCuenta.selectedItemPosition
            val idRol = when (selectedRolePosition) {
                0 -> 4 // "Paciente" corresponds to idRol 4
                1 -> 2 // "Medico" corresponds to idRol 2
                2 -> 1 // "Gestor de usuario" corresponds to idRol 1
                3 -> 3 // "Creador de Test" corresponds to idRol 3
                else -> -1 // Handle any other cases or errors
            }


            // Prepare the updated user data and create a ModificarUsuarioAsyncTask
            val updatedUsuario = Usuario(
                idUsuarios = usuarioEncontrado?.idUsuarios ?: 0, // Use the existing ID if available
                idRol = idRol, // Update the role based on the selected item in tipoDeCuenta Spinner
                usuario = etUsuario.text.toString(),
                contrasenia = etContrasenia.text.toString(),
                correo = etCorreo.text.toString(),
                cedula = etCedula.text.toString(),
                nombres = etNombres.text.toString(),
                apellidos = etApellidos.text.toString(),
                fechaNac = etFechaNac.text.toString()
            )


            val modificarUsuarioTask = ModificarUsuarioAsyncTask(object : ModificarUsuarioAsyncTask.ModificarUsuarioListener {
                override fun onUsuarioModificado(success: Boolean) {
                    if (success) {
                        // User data updated successfully
                        // You can perform any necessary actions here
                        Toast.makeText(this@AdministrarUsuarios, "Usuario modificado con éxito", Toast.LENGTH_SHORT).show()
                        // Call the function to clear EditText fields
                        clearEditTextFields()
                    } else {
                        // Handle the case where the update was not successful
                        Toast.makeText(this@AdministrarUsuarios, "Error al modificar el usuario", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(mensajeError: String) {
                    // Handle errors
                    Toast.makeText(this@AdministrarUsuarios, "Error: $mensajeError", Toast.LENGTH_SHORT).show()
                }
            })

            // Execute the modification task with the updated user data
            modificarUsuarioTask.execute(updatedUsuario)
        }





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

    private fun clearEditTextFields() {
        editText.text.clear()
        etCedula.text.clear()
        etNombres.text.clear()
        etApellidos.text.clear()
        etFechaNac.text.clear()
        etUsuario.text.clear()
        etCorreo.text.clear()
        etContrasenia.text.clear()
        // You can also reset the Spinner to the first item (if needed)
        tipoDeCuenta.setSelection(0)
    }


    private fun mostrarDatosDelUsuarioEnEditText() {
        if (usuarioEncontrado != null) {
            etCedula.setText(usuarioEncontrado!!.cedula)
            etNombres.setText(usuarioEncontrado!!.nombres)
            etApellidos.setText(usuarioEncontrado!!.apellidos)
            etUsuario.setText(usuarioEncontrado!!.usuario)
            etCorreo.setText(usuarioEncontrado!!.correo)
            etContrasenia.setText(usuarioEncontrado!!.contrasenia)
            etFechaNac.setText(usuarioEncontrado!!.fechaNac.toString())
            // Update the "tipoDeCuenta" Spinner based on the "IdRol" of the user
            val idRol = usuarioEncontrado!!.idRol
            val roles = listOf("Paciente", "Medico", "Gestor de usuario", "Creador de Test")

            // Map the IdRol to the Spinner position
            val spinnerPosition = when (idRol) {
                1 -> roles.indexOf("Gestor de usuario")
                2 -> roles.indexOf("Medico")
                3 -> roles.indexOf("Creador de Test")
                4 -> roles.indexOf("Paciente")
                else -> -1 // Handle any other cases or errors
            }

            if (spinnerPosition >= 0) {
                tipoDeCuenta.setSelection(spinnerPosition)
            } else {
                // Handle the case where IdRol doesn't match any known role
                // You can display an error message or handle it accordingly.
            }
        }
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