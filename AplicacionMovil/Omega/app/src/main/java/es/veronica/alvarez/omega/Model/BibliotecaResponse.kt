package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BibliotecaResponse (
    @SerializedName("id")
    val id: Int,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("publica")
    val publica: Boolean
):Serializable