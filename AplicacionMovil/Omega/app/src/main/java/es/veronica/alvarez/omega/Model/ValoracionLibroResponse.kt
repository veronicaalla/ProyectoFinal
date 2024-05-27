package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class ValoracionLibroResponse (
    @SerializedName("idLibro")
    val idLibro: Int,

    @SerializedName("valoracion_total")
    val valoracion: Double
)