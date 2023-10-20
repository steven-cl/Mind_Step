package com.project.mindstep.AdminUser

import java.util.Date

data class Usuario(
    val idUsuarios: Int,
    val idRol: Int,
    val usuario: String,
    val contrasenia: String,
    val correo: String,
    val cedula: String,
    val nombres: String,
    val apellidos: String,
    val fechaNac: String
)
