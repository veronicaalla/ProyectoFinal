package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BibliotecaResponse (
    @SerializedName("id")
    val id: Int=0,

    @SerializedName("nombre")
    var nombre: String = "",

    @SerializedName("publica")
    val publica: Boolean = false
):Serializable