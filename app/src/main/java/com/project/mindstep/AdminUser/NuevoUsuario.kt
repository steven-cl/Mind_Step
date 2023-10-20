package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.project.mindstep.Login.Login
import com.project.mindstep.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@Suppress("DEPRECATION")
class NuevoUsuario : AppCompatActivity(), ValidarDatosNuevoUsuarioAsyncTask.ValidationListener {

    private lateinit var expediente: EditText
    private lateinit var cedula: EditText
    private lateinit var nombres: EditText
    private lateinit var apellidos: EditText
    private lateinit var username: EditText
    private lateinit var correo: EditText
    private lateinit var contrasenia: EditText
    private lateinit var fechaNacimiento: EditText
    private lateinit var tipoCuenta: Spinner
    private lateinit var registrarButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        val ajusteImagen: ImageView = findViewById(R.id.ajuste_imagen)
        val administradorImagen: ImageView = findViewById(R.id.administrador_imagen)
        val citasImagen: ImageView = findViewById(R.id.citas_imagen)

        ajusteImagen.setOnClickListener { navigateToAjusteActivity() }
        administradorImagen.setOnClickListener { navigateToAdministradorActivity() }
        citasImagen.setOnClickListener { navigateToCitasActivity() }

        // Initialize views
        expediente = findViewById(R.id.ETExpediente)
        cedula = findViewById(R.id.ETCedula)
        nombres = findViewById(R.id.ETNombres)
        apellidos = findViewById(R.id.ETApellidos)
        username = findViewById(R.id.ETUser)
        correo = findViewById(R.id.ETCorreo)
        contrasenia = findViewById(R.id.ETContraseña)
        fechaNacimiento = findViewById(R.id.ETFechaNac)
        tipoCuenta = findViewById(R.id.RolesUsuarios)
        registrarButton = findViewById(R.id.btnRegistrar)

        // Set up role spinner
        val roles = listOf("Paciente", "Medico", "Gestor de usuario", "Creador de Test")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roles)
        tipoCuenta.adapter = adapter

        // Set up button click listener
        registrarButton.setOnClickListener {
            if (validateFields()) {
                val validateTask = ValidarDatosNuevoUsuarioAsyncTask(this)
                validateTask.execute(
                    cedula.text.toString(),
                    username.text.toString(),
                    correo.text.toString(),
                    contrasenia.text.toString(),
                    expediente.text.toString(),
                    tipoCuenta.selectedItem.toString()
                )
            }
        }


        val perfilClick : Spinner = findViewById(R.id.perfil_clickable)

        val opcionesPerfil = listOf(" ","Cerrar Sesión")
        val adaptersesion = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesPerfil)
        adaptersesion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        perfilClick.adapter = adaptersesion

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

    // ValidationListener callback
    override fun onValidationResult(result: Boolean) {
        if (result) {
            Toast.makeText(this, "Uno de los datos ya existe en la base de datos", Toast.LENGTH_SHORT).show()
        } else {
            val tipoCuentaValue = convertirRolANumero(tipoCuenta.selectedItem.toString())

            // Continue with the insertion of data
            val insertTask = InsertarNuevoUsuarioAsyncTask(this)
            insertTask.execute(
                tipoCuentaValue.toString(),
                username.text.toString(),
                contrasenia.text.toString(),
                correo.text.toString(),
                cedula.text.toString(),
                nombres.text.toString(),
                apellidos.text.toString(),
                fechaNacimiento.text.toString(),
                expediente.text.toString()

            )
        }
    }
    private fun convertirRolANumero(rol: String): Int {
        return when (rol) {
            "Gestor de usuario" -> 1
            "Medico" -> 2
            "Creador de Test" -> 3
            "Paciente" -> 4
            else -> 4  // Manejar otros casos según tus necesidades
        }
    }
    // Add this method to show the Toast in the activity
    fun showInsertionToast(success: Boolean) {
        if (success) {
            // Data inserted successfully, show a Toast
            Toast.makeText(
                this,
                "Usuario insertado correctamente",
                Toast.LENGTH_SHORT
            ).show()

            // Clear EditText fields
            expediente.text.clear()
            cedula.text.clear()
            nombres.text.clear()
            apellidos.text.clear()
            username.text.clear()
            correo.text.clear()
            contrasenia.text.clear()
            fechaNacimiento.text.clear()
            tipoCuenta.setSelection(0) // Coloca la selección del spinner al índice 0
        } else {
            // Handle insertion failure if needed
        }
    }

    private fun validateFields(): Boolean {
        // Utiliza TextInputLayout para mostrar mensajes de error debajo de los EditText
        val inputLayoutExpediente = findViewById<TextInputLayout>(R.id.inputLayoutExpediente)
        val inputLayoutCedula = findViewById<TextInputLayout>(R.id.inputLayoutCedula)
        val inputLayoutNombre = findViewById<TextInputLayout>(R.id.inputLayoutNombre)
        val inputLayoutApellidos = findViewById<TextInputLayout>(R.id.inputLayoutApellidos)
        val inputLayoutCorreo = findViewById<TextInputLayout>(R.id.inputLayoutCorreo)
        val inputLayoutContrasenia = findViewById<TextInputLayout>(R.id.inputLayoutContraseña)
        val inputLayoutFechaNac = findViewById<TextInputLayout>(R.id.inputLayoutFechaNac)

        // Validating the fields based on the mentioned criteria
        val expedienteValue = expediente.text.toString().trim()
        val cedulaValue = cedula.text.toString()
        val nombresValue = nombres.text.toString()
        val apellidosValue = apellidos.text.toString()
        val correoValue = correo.text.toString()
        val contraseniaValue = contrasenia.text.toString()
        val fechaNacimientoValue = fechaNacimiento.text.toString()

        val expedientePattern = Pattern.compile("^[a-zA-Z0-9-]+$")
        val cedulaPattern = Pattern.compile("^[a-zA-Z0-9-]+$")
        val nombresPattern = Pattern.compile("^[a-zA-Z ]+$")
        val apellidosPattern = Pattern.compile("^[a-zA-Z ]+$")
        val correoPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        val contraseniaPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$")

        // You may customize the date format pattern according to your requirements
        val dateFormatPattern = "dd-MM-yyyy"
        val dateFormat = SimpleDateFormat(dateFormatPattern, Locale.getDefault())

        try {
            dateFormat.parse(fechaNacimientoValue)
            inputLayoutFechaNac.error = null  // Limpiar el mensaje de error si la validación es exitosa
        } catch (e: Exception) {
            inputLayoutFechaNac.error = "Fecha no válida"
            return false
        }

        if (tipoCuenta.selectedItem.toString() == "Paciente") {
            if (expedienteValue.isEmpty() ||
                cedulaValue.isEmpty() ||
                nombresValue.isEmpty() ||
                apellidosValue.isEmpty() ||
                correoValue.isEmpty() ||
                contraseniaValue.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios para los roles seleccionados", Toast.LENGTH_SHORT).show()
                return false
            }


            if (!expedientePattern.matcher(expedienteValue).matches()) {
                inputLayoutExpediente.error = "Número de Expediente no válido"
                return false
            } else {
                inputLayoutExpediente.error = null  // Limpiar el mensaje de error si la validación es exitosa
            }


        }

        if (tipoCuenta.selectedItem.toString() == "Gestor de usuario" ||
            tipoCuenta.selectedItem.toString() == "Medico" ||
            tipoCuenta.selectedItem.toString() == "Creador de Test") {
            if (!expedienteValue.isEmpty()) {
                Toast.makeText(this, "No añadir número de expediente al rol ${tipoCuenta.selectedItem}", Toast.LENGTH_SHORT).show()
                return false
            }
        }


        if (!cedulaPattern.matcher(cedulaValue).matches()) {
            inputLayoutCedula.error = "Cédula no válida"
            return false
        } else {
            inputLayoutCedula.error = null  // Limpiar el mensaje de error si la validación es exitosa
        }

        if (!nombresPattern.matcher(nombresValue).matches()) {
            inputLayoutNombre.error = "Nombres no válidos"
            return false
        } else {
            inputLayoutNombre.error = null  // Limpiar el mensaje de error si la validación es exitosa
        }

        // Validación de Apellidos
        if (!apellidosPattern.matcher(apellidosValue).matches()) {
            inputLayoutApellidos.error = "Apellidos no válidos"
            return false
        } else {
            inputLayoutApellidos.error = null
        }

        if (!correoPattern.matcher(correoValue).matches()) {
            inputLayoutCorreo.error = "Correo no válido"
            return false
        } else {
            inputLayoutCorreo.error = null  // Limpiar el mensaje de error si la validación es exitosa
        }

        if (!contraseniaPattern.matcher(contraseniaValue).matches()) {
            inputLayoutContrasenia.error = "Contraseña no válida"
            return false
        } else {
            inputLayoutContrasenia.error = null  // Limpiar el mensaje de error si la validación es exitosa
        }



        return true
    }


    private fun navigateToAjusteActivity() {
        val intent = Intent(this, AjustesAdmin::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToAdministradorActivity() {
        val intent = Intent(this, AdministrarUsuarios::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

    private fun navigateToCitasActivity() {
        val intent = Intent(this, AsignarCitas::class.java)
        startActivity(intent)
        finishAfterTransition()
    }
}
