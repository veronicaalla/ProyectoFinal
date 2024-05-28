package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PerfilUsuarioResponse (
    @SerializedName("idperfil")
    val idperfil: Int,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("informacion")
    var informacion: String,

    @SerializedName("idUsuario")
    var idUsuario: Int
):Serializable