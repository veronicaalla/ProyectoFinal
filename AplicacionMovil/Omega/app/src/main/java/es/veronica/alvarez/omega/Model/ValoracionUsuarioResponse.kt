package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class ValoracionUsuarioResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("libro") var libro: LibroResponse,
    @SerializedName("usuario") var usuario: UsuarioResponse,
    @SerializedName("playList") var playList: String?,
    @SerializedName("personajeFav") var personajeFav: String?,
    @SerializedName("personajeOdiado") var personajeOdiado: String?,
    @SerializedName("recomendacion") var recomendacion: Boolean,
    @SerializedName("fraseIconica") var fraseIconica: String?,
    @SerializedName("opinion") var opinion: String?,
    @SerializedName("puntuacion") var puntuacion: Double,
    @SerializedName("fechaValoracion") val fechaValoracion: String?
)
