package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime

data class UsuarioResponse(
    @SerializedName("id")
    val id: Int?=null,

    @SerializedName("alias")
    val alias: String?=null,

    @SerializedName("nombre")
    val nombre: String?=null,

    @SerializedName("apellidos")
    val apellidos: String?=null,

    @SerializedName("correo")
    val correo: String?=null,

    @SerializedName("clave")
    val clave: String?=null,

    @SerializedName("telefono")
    val telefono: String?=null,

    @SerializedName("tipo")
    val tipo: Int = 3,

    @SerializedName("publico")
    val publico: Boolean= false
)