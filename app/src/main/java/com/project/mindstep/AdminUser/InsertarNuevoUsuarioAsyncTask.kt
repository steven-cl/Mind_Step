@file:Suppress("DEPRECATION")

package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.project.mindstep.DataBaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.text.SimpleDateFormat
import java.util.*

class InsertarNuevoUsuarioAsyncTask(private @field:SuppressLint("StaticFieldLeak") val context: NuevoUsuario) :
    AsyncTask<String, Void, Boolean>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String): Boolean {
        // Extract data from params
        val expediente = params[0]
        val cedula = params[1]
        val nombres = params[2]
        val apellidos = params[3]
        val username = params[4]
        val correo = params[5]
        val contrasenia = params[6]
        val fechaNacimiento = params[7]
        val tipoCuenta = params[8]

        // Calculate age
        val edad = calcularEdad(fechaNacimiento)

        // Split the fechaNacimiento into day, month, and year
        val fechaParts = fechaNacimiento.split("/")
        val diaNacimiento = fechaParts[0]
        val mesNacimiento = fechaParts[1]
        val yearNacimiento = fechaParts[2]

        var connection: Connection? = null

        try {
            // Open a connection
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
                // Prepare the SQL statement for insertion
                val sql = "INSERT INTO [dbo].[Usuarios] " +
                        "(NumeroExpediente, Cedula, Nombres, Apellidos, Usuario, Correo, Contraseña, DiaNacimiento, MesNacimiento, AñoNacimiento, Edad, TipoUser) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, expediente)
                preparedStatement.setString(2, cedula)
                preparedStatement.setString(3, nombres)
                preparedStatement.setString(4, apellidos)
                preparedStatement.setString(5, username)
                preparedStatement.setString(6, correo)
                preparedStatement.setString(7, contrasenia)
                preparedStatement.setString(8, diaNacimiento)
                preparedStatement.setString(9, mesNacimiento)
                preparedStatement.setString(10, yearNacimiento)
                preparedStatement.setInt(11, edad)
                preparedStatement.setString(12, tipoCuenta)

                // Execute the insertion
                preparedStatement.executeUpdate()

                return true
            } else {
                Log.e("InsertarNuevoUsuario", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("InsertarNuevoUsuario", "Error al insertar usuario", e)
        } finally {
            // Close the connection
            DataBaseConnection.closeConnection(connection)
        }

        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)

        if (result) {
            // Data inserted successfully, show a Toast using the activity's context
            context.showInsertionToast(true)
        } else {
            // Handle insertion failure if needed
        }
    }

    private fun calcularEdad(fechaNacimiento: String): Int {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaNacimientoDate = formato.parse(fechaNacimiento)
        val fechaActual = Calendar.getInstance().time

        val diff = fechaActual.time - fechaNacimientoDate!!.time
        val edadMillis = diff / (24 * 60 * 60 * 1000 * 365.25) // Milliseconds to years
        return edadMillis.toInt()
    }
}
