package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.time.LocalDateTime;


data class DatosUsuarioResponse(
    @SerializedName("id") val id: String,
    @SerializedName("auditCreated") val auditCreated: String,
    @SerializedName("auditCreator") val auditCreator: String,
    @SerializedName("auditUpdated") val auditUpdated: String,
    @SerializedName("auditUpdater") val auditUpdater: String,
    @SerializedName("username") val username: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellidos") val apellidos: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("clave") val clave: String,
    @SerializedName("telefono") val telefono: String,
    @SerializedName("tipo") val tipo: String,
    @SerializedName("publico") val publico: String
)