package es.veronica.alvarez.omega.DataApi

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("usuario/{id}")
    fun obtenerUsuarioPorId(@Path("id") id: Int): Call<String>
}