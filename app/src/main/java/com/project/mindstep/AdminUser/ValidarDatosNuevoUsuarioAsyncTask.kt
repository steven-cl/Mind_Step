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
        val numeroCedula = params[0]
        val nombreUsuario = params[1]
        val correo = params[2]
        val contrasenia = params[3]
        val expediente = params[4]  // Agregar el número de expediente
        val tipoUsuario = params[5]  // Agregar el tipo de usuario

        var connection: Connection? = null

        try {
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
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

                // Validar Expediente solo si el tipo de usuario es "Paciente"
                if (tipoUsuario == "4" && existeDatoExpediente(connection, expediente)) {
                    return true
                }

                return false
            } else {
                Log.e("ValidarDatosUnicosAsyncTask", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ValidarDatosUnicosAsyncTask", "Error al validar datos únicos", e)
        } finally {
            DataBaseConnection.closeConnection(connection)
        }

        return false
    }

    private fun existeDato(connection: Connection, columna: String, valor: String): Boolean {
        val sql = "SELECT COUNT(*) FROM [dbo].[Usuarios] WHERE $columna LIKE ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, valor)

        val resultSet: ResultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            val count = resultSet.getInt(1)
            return count > 0
        }

        return false
    }

    private fun existeDatoExpediente(connection: Connection, expediente: String): Boolean {
        val sql = "SELECT COUNT(*) FROM [dbo].[Paciente] WHERE Expediente LIKE ?"
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1, expediente)

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