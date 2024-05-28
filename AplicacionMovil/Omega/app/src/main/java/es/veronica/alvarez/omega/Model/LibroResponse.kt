package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class LibroResponse (
    @SerializedName("id")
    val id: Int,

    @SerializedName("isbn")
    val isbn: String,

    @SerializedName("titulo")
    val titulo: String,

    @SerializedName("autor")
    val autor: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("genero")
    val genero: Int,

    @SerializedName("fecha_publicacion")
    val fechaPublicacion: String?,  // Usa String o LocalDate si lo necesitas

    @SerializedName("paginas")
    val paginas: Int
)