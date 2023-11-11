package com.project.mindstep.AdminUser

import android.os.AsyncTask
import android.util.Log
import com.project.mindstep.DataBaseConnection
import java.sql.Connection
import java.sql.PreparedStatement

class ModificarUsuarioAsyncTask(
    private val listener: ModificarUsuarioListener
) : AsyncTask<Usuario, Void, Boolean>() {

    interface ModificarUsuarioListener {
        fun onUsuarioModificado(success: Boolean)
        fun onError(mensajeError: String)
    }

    override fun doInBackground(vararg params: Usuario): Boolean {
        val usuario = params[0]
        val cedula = usuario.cedula

        val sql = """
            UPDATE [dbo].[Usuarios]
            SET [IdRol] = ?, [Usuario] = ?, [Contraseña] = ?, [Correo] = ?,
            [Cedula] = ?, [Nombres] = ?, [Apellidos] = ?, [FechaNac] = ?
            WHERE [Cedula] LIKE ?
        """.trimIndent()

        var connection: Connection? = null

        try {
            connection = DataBaseConnection.getConnection()
            if (connection != null) {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                preparedStatement.setInt(1, usuario.idRol)
                preparedStatement.setString(2, usuario.usuario)
                preparedStatement.setString(3, usuario.contrasenia)
                preparedStatement.setString(4, usuario.correo)
                preparedStatement.setString(5, usuario.cedula)
                preparedStatement.setString(6, usuario.nombres)
                preparedStatement.setString(7, usuario.apellidos)
                preparedStatement.setString(8, usuario.fechaNac)
                preparedStatement.setString(9, cedula)

                val rowsUpdated = preparedStatement.executeUpdate()
                return rowsUpdated > 0
            } else {
                Log.e("ModificarUsuarioAsyncTask", "Error de conexión")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("ModificarUsuarioAsyncTask", "Error al modificar usuario", e)
        } finally {
            DataBaseConnection.closeConnection(connection)
        }

        return false
    }

    override fun onPostExecute(success: Boolean) {
        if (success) {
            listener.onUsuarioModificado(true)
        } else {
            listener.onError("Error al modificar el usuario")
        }
    }
}