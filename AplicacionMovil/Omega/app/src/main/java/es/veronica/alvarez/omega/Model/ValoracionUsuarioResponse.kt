package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class ValoracionUsuarioResponse (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("playList") var playList: String? = null,
    @SerializedName("personajeFav") var personajeFav: String?= null,
    @SerializedName("personajeOdiado") var personajeOdiado: String?= null,
    @SerializedName("recomendacion") var recomendacion: Boolean = false,
    @SerializedName("fraseIconica") var fraseIconica: String?= null,
    @SerializedName("opinion") var opinion: String?= null,
    @SerializedName("puntuacion") var puntuacion: Double = 0.0,
    @SerializedName("fechaValoracion") val fechaValoracion: String?= null
)
