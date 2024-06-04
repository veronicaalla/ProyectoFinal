package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ComentarioResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("idLibro") val idLibro: Int,
    @SerializedName("idUsuario") val idUsuario: Int,
    @SerializedName("comentario") val comentario: String,
    @SerializedName("fecha") val fecha: String,
)