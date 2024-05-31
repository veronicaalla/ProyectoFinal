package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class LibrosBibliotecaResponse (

    @SerializedName("id")
    val id: Int,
    @SerializedName("libro")
    val libro: LibroResponse
)