package com.project.mindstep

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import com.project.mindstep.Paciente.Agenda
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class FetchUsuariosAsyncTask(private val context: Context, private val taskListener: TaskListener) :
    AsyncTask<String, Void, String>() {

    interface TaskListener {
        fun onTaskCompleted(result: String)
    }

    override fun doInBackground(vararg params: String): String {
        var result = ""

        // Extract email and password from params
        val email = params[0]
        val password = params[1]

        var connection: Connection? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            connection = DatabaseConnection.getConnection()
            if (connection != null) {
                statement = connection.createStatement()
                resultSet = statement.executeQuery(
                    "SELECT [NumeroExpediente], [Cedula], [Nombres], [Apellidos], [Edad], [DiaNacimiento], " +
                            "[MesNacimiento], [AñoNacimiento], [TipoUser], [Correo], [Usuario], [Contraseña] FROM [dbo].[Usuarios]"
                )

                while (resultSet.next()) {
                    val numeroExpediente = resultSet.getString("NumeroExpediente")
                    val cedula = resultSet.getString("Cedula")
                    val nombres = resultSet.getString("Nombres")
                    val apellidos = resultSet.getString("Apellidos")
                    val edad = resultSet.getString("Edad")
                    val diaNacimiento = resultSet.getString("DiaNacimiento")
                    val mesNacimiento = resultSet.getString("MesNacimiento")
                    val yearNacimiento = resultSet.getString("AñoNacimiento")
                    val tipoUser = resultSet.getString("TipoUser")
                    val correo = resultSet.getString("Correo")
                    val usuario = resultSet.getString("Usuario")
                    val contrasenia = resultSet.getString("Contraseña")

                    if (correo == email && contrasenia == password) {
                        // Authentication successful
                        val userData = Bundle().apply {
                            putString("NumeroExpediente", numeroExpediente)
                            putString("Cedula", cedula)
                            putString("Nombres", nombres)
                            putString("Apellidos", apellidos)
                            putString("Edad", edad)
                            putString("DiaNacimiento", diaNacimiento)
                            putString("MesNacimiento", mesNacimiento)
                            putString("AñoNacimiento", yearNacimiento)
                            putString("TipoUser", tipoUser)
                            putString("Correo", correo)
                            putString("Usuario", usuario)
                            putString("Contraseña", contrasenia)
                            // Add other user data here...
                        }

                        // Create Intent to start the Agenda activity
                        val intent = Intent(context, Agenda::class.java).apply {
                            putExtras(userData)
                        }

                        // Start the Agenda activity
                        context.startActivity(intent)
                    } else {

                    }
                }

            } else {
                result = "Error de conexión"
                Log.e("FetchUsuariosAsyncTask", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = "Error al obtener datos"
            Log.e("FetchUsuariosAsyncTask", "Error al obtener datos", e)
        } finally {
            resultSet?.close()
            statement?.close()
            DatabaseConnection.closeConnection(connection)
        }

        Log.d("FetchUsuariosAsyncTask", "Resultado: $result")
        return result
    }

    override fun onPostExecute(result: String) {
        taskListener.onTaskCompleted(result)
    }
}




