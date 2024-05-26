package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

data class UsuarioResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("alias")
    val alias: String?,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("apellidos")
    val apellidos: String,

    @SerializedName("fecha_nacimiento")
    val fechaNacimiento: LocalDate,

    @SerializedName("correo")
    val correo: String,

    @SerializedName("clave")
    val clave: String,

    @SerializedName("telefono")
    val telefono: String?,

    @SerializedName("tipo")
    val tipo: Int,

    @SerializedName("publico")
    val publico: Boolean?
)