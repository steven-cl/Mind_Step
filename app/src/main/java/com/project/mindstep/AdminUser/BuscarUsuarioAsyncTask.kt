package com.project.mindstep.AdminUser

import android.os.AsyncTask
import android.util.Log
import com.project.mindstep.DataBaseConnection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class BuscarUsuarioAsyncTask(private val listener: BuscarUsuarioListener) : AsyncTask<String, Void, Usuario>() {

    interface BuscarUsuarioListener {
        fun onUsuarioEncontrado(usuario: Usuario?)
        fun onError(mensajeError: String)
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): Usuario? {
        val cedula = params[0]
        val sql = """
            SELECT [IdUsuarios],[IdRol],[Usuario],[Contraseña],[Correo],[Cedula],[Nombres],[Apellidos],[FechaNac]
            FROM [dbo].[Usuarios]
            WHERE [Cedula] LIKE ?
        """.trimIndent()

        var connection: Connection? = null

        try {
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                preparedStatement.setString(1, cedula)

                val resultSet: ResultSet = preparedStatement.executeQuery()

                if (resultSet.next()) {
                    val usuario = Usuario(
                        resultSet.getInt("IdUsuarios"),
                        resultSet.getInt("IdRol"),
                        resultSet.getString("Usuario"),
                        resultSet.getString("Contraseña"),
                        resultSet.getString("Correo"),
                        resultSet.getString("Cedula"),
                        resultSet.getString("Nombres"),
                        resultSet.getString("Apellidos"),
                        resultSet.getString("FechaNac")
                    )
                    return usuario
                }
            } else {
                Log.e("BuscarUsuarioAsyncTask", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("BuscarUsuarioAsyncTask", "Error al buscar usuario", e)
        } finally {
            DataBaseConnection.closeConnection(connection)
        }

        return null
    }

    override fun onPostExecute(usuarioEncontrado: Usuario?) {
        if (usuarioEncontrado != null) {
            listener.onUsuarioEncontrado(usuarioEncontrado)
        } else {
            listener.onError("No se encontró un usuario con esa cédula")
        }
    }
}