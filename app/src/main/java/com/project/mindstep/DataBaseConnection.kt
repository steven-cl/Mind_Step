package com.project.mindstep

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DataBaseConnection {
    private const val DB_URL = "jdbc:jtds:sqlserver://192.168.50.111:1434/BDMS"
    private const val DB_USER = "sa"
    private const val DB_PASSWORD = "Master123321"


    fun getConnection(): Connection? {
        return try {
            Log.d("DatabaseConnection", "Intentando conectar a $DB_URL como $DB_USER")
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
        } catch (e: ClassNotFoundException) {
            Log.e("DatabaseConnection", "Error al cargar el controlador", e)
            null
        } catch (e: SQLException) {
            Log.e("DatabaseConnection", "Error al conectar a la base de datos", e)
            null
        }
    }


    fun closeConnection(connection: Connection?) {
        connection?.let {
            try {
                it.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }
}