package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class UsuarioResponse(
    @SerializedName("response")
    val usuarioResponse: DatosUsuarioResponse,

    @SerializedName("code")
    val code: String
)