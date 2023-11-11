@file:Suppress("DEPRECATION")

package com.project.mindstep.AdminUser

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.project.mindstep.DataBaseConnection
import java.sql.Connection
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class InsertarNuevoUsuarioAsyncTask(private @field:SuppressLint("StaticFieldLeak") val context: NuevoUsuario) :
    AsyncTask<String, Void, Boolean>() {

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): Boolean {
        // Extraer datos de los parámetros
        val idRol = params[0]
        val usuario = params[1]
        val contrasenia = params[2]
        val correo = params[3]
        val cedula = params[4]
        val nombres = params[5]
        val apellidos = params[6]
        val fechaNac = params[7]
        val expediente = params[8]  // Añadir el parámetro de expediente

        val dateFormatPattern = "yyyy-MM-dd" // Formato que espera la base de datos
        val dateFormat = SimpleDateFormat(dateFormatPattern, Locale.getDefault())

        try {
            val fechaNacimiento = dateFormat.parse(fechaNac.toString())

            // Abrir una conexión
            val connection = DataBaseConnection.getConnection()
            connection?.use { conn ->
                // Preparar la sentencia SQL para la inserción con los valores dinámicos
                val sql = """
    INSERT INTO [dbo].[Usuarios] 
    ([IdRol], [Usuario], [Contraseña], [Correo], [Cedula], [Nombres], [Apellidos], [FechaNac]) 
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
""".trimIndent()

                val preparedStatement = conn.prepareStatement(sql)
                preparedStatement.setString(1, idRol) // Configura IdRol en la sentencia SQL
                preparedStatement.setString(2, usuario)
                preparedStatement.setString(3, contrasenia)
                preparedStatement.setString(4, correo)
                preparedStatement.setString(5, cedula)
                preparedStatement.setString(6, nombres)
                preparedStatement.setString(7, apellidos)

// Convierte la fecha a java.sql.Date y configúrala en el PreparedStatement
                val sqlDate = java.sql.Date(fechaNacimiento!!.time)
                preparedStatement.setDate(8, sqlDate)

// ...


                // Ejecutar la inserción en la tabla Usuarios
                preparedStatement.executeUpdate()

                // Obtener el IdUsuario recién insertado
                val idUsuario = obtenerUltimoIdUsuario(conn)

                // Insertar en la tabla Paciente (solo si es un paciente)
                if (idRol == "4") {  // Suponiendo que "4" es el IdRol para "Paciente"
                    if (expediente != null) {
                        insertarPaciente(conn, idUsuario, expediente)
                    }
                }

                return true
            } ?: run {
                Log.e("InsertarNuevoUsuario", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("InsertarNuevoUsuario", "Error al insertar usuario", e)
        }

        return false
    }

    private fun obtenerUltimoIdUsuario(connection: Connection): Int {
        val sql = "SELECT MAX(IdUsuarios + 1) FROM Usuarios"
        val preparedStatement = connection.prepareStatement(sql)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            resultSet.getInt(1)
        } else {
            0  // Si no se encontró ningún IdUsuario
        }
    }

    private fun insertarPaciente(connection: Connection, idUsuario: Int, expediente: String) {
        // Obtén el próximo valor de idPaciente
        val nextIdPaciente = obtenerProximoIdPaciente(connection)

        // Inserta el nuevo registro en la tabla Paciente con el valor de idPaciente obtenido
        val sql = "INSERT INTO Paciente (idPaciente, IdUsuario, Expediente) VALUES (?, ?, ?)"
        val preparedStatement = connection.prepareStatement(sql)
        preparedStatement.setInt(1, nextIdPaciente)
        preparedStatement.setInt(2, idUsuario)
        preparedStatement.setString(3, expediente)
        preparedStatement.executeUpdate()
    }

    private fun obtenerProximoIdPaciente(connection: Connection): Int {
        val sql = "SELECT ISNULL(MAX(idPaciente) + 1, 1) FROM Paciente"
        val preparedStatement = connection.prepareStatement(sql)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            resultSet.getInt(1)
        } else {
            1  // Si no se encontró ningún valor, devuelve 1 como valor predeterminado
        }
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
}