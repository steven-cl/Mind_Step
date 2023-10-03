@file:Suppress("DEPRECATION")

package com.project.mindstep.AdminUser

import android.os.AsyncTask
import android.util.Log
import com.project.mindstep.DataBaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class ValidarDatosNuevoUsuarioAsyncTask(
    private val listener: ValidationListener
) : AsyncTask<String, Void, Boolean>() {

    interface ValidationListener {
        fun onValidationResult(result: Boolean)
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String): Boolean {
        val numeroExpediente = params[0]
        val numeroCedula = params[1]
        val nombreUsuario = params[2]
        val correo = params[3]
        val contrasenia = params[4]

        var connection: Connection? = null

        try {
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
                // Validar NumeroExpediente
                if (existeDato(connection, "NumeroExpediente", numeroExpediente)) {
                    return true
                }

                // Validar NumeroCedula
                if (existeDato(connection, "Cedula", numeroCedula)) {
                    return true
                }

                // Validar NombreUsuario
                if (existeDato(connection, "Usuario", nombreUsuario)) {
                    return true
                }

                // Validar Correo
                if (existeDato(connection, "Correo", correo)) {
                    return true
                }

                // Validar Contraseña
                if (existeDato(connection, "Contraseña", contrasenia)) {
                    return true
                }

                return false
            } else {
                Log.e("ValidarDatosNuevoUsuario", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ValidarDatosNuevoUsuario", "Error al validar datos", e)
        } finally {
            DataBaseConnection.closeConnection(connection)
        }

        return true
    }

    private fun existeDato(connection: Connection, columna: String, valor: String): Boolean {
        val sql = "SELECT COUNT(*) FROM [dbo].[Usuarios] WHERE $columna = ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, valor)

        val resultSet: ResultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            val count = resultSet.getInt(1)
            return count > 0
        }

        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        listener.onValidationResult(result)
    }
}
