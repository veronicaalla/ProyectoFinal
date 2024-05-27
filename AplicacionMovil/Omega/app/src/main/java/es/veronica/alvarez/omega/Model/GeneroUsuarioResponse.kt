package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class GeneroUsuarioResponse (
    @SerializedName("idGenero")
    val idGenero: Int,
    @SerializedName("idUsuario")
    val idUsuario: Int
)