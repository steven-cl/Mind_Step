@file:Suppress("DEPRECATION")

package com.project.mindstep

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

class FetchUsuariosAsyncTask(private @field:SuppressLint("StaticFieldLeak") val context: Context,
                             private val taskListener: TaskListener) :
    AsyncTask<String, Void, String>() {

    interface TaskListener {
        fun onTaskCompleted(result: String)
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String): String {
        var result = ""

        // Extract email and password from params
        val email = params[0]
        val password = params[1]

        var connection: Connection? = null
        var statement: Statement? = null
        var resultSet: ResultSet? = null

        try {
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
                statement = connection.createStatement()
                resultSet = statement.executeQuery(
                    "SELECT [IdUsuarios],[IdRol],[Usuario],[Contraseña],[Correo],[Cedula],[Nombres],[Apellidos],[FechaNac]" +
                            "  FROM [dbo].[Usuarios]"
                )

                while (resultSet.next()) {
                    val idUsuarios = resultSet.getString("IdUsuarios")
                    val idRol = resultSet.getString("IdRol")
                    val usuario = resultSet.getString("Usuario")
                    val contrasenia = resultSet.getString("Contraseña")
                    val correo = resultSet.getString("Correo")
                    val cedula = resultSet.getString("Cedula")
                    val nombres = resultSet.getString("Nombres")
                    val apellidos = resultSet.getString("Apellidos")
                    val fechaNac = resultSet.getString("FechaNac")


                    if ((correo == email && contrasenia == password) || (email == usuario && contrasenia == password)) {
                        // Authentication successful
                        val userData = Bundle().apply {
                            putString("IdUsuarios", idUsuarios)
                            putString("IdRol", idRol)
                            putString("Usuario", usuario)
                            putString("Contraseña", contrasenia)
                            putString("Correo", correo)
                            putString("Cedula", cedula)
                            putString("Nombres", nombres)
                            putString("Apellidos", apellidos)
                            putString("FechaNac", fechaNac)

                            // Add other user data here...
                        }

                        if (userData.getString("IdRol", "") == "4"){
                            result="4"
                        }else if(userData.getString("IdRol", "") == "2"){
                            result = "2"
                        }else if(userData.getString("IdRol", "") == "1"){
                            result = "1"
                        }else if(userData.getString("IdRol", "") == "3"){
                            result = "3"
                        }
                    }/*else {
                        Toast.makeText(context, "Correo o contraseña invalidos", Toast.LENGTH_SHORT).show()
                    }*/
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
            DataBaseConnection.closeConnection(connection)
        }

        Log.d("FetchUsuariosAsyncTask", "Resultado: $result")
        return result
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String) {
        taskListener.onTaskCompleted(result)
    }
}