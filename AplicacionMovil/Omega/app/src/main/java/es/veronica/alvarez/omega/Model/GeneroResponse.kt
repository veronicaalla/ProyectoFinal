package es.veronica.alvarez.omega.Model

import com.google.gson.annotations.SerializedName

data class GeneroResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String
)