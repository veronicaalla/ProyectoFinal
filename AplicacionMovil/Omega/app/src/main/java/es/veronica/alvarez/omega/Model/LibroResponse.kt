package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LibroResponse (
    @SerializedName("id")
    val id: Int?=null,

    @SerializedName("isbn")
    var isbn: String?=null,

    @SerializedName("titulo")
    var titulo: String?=null,

    @SerializedName("autor")
    var autor: String?=null,

    @SerializedName("descripcion")
    var descripcion: String?=null,

    @SerializedName("genero")
    var genero: Int?=null,

    @SerializedName("fecha_publicacion")
    var fechaPublicacion: String?=null,  // Usa String o LocalDate si lo necesitas

    @SerializedName("paginas")
    var paginas: Int?=null
):Serializable