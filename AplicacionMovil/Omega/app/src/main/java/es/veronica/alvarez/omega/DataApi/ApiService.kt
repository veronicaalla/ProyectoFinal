package es.veronica.alvarez.omega.DataApi

import es.veronica.alvarez.omega.Model.UsuarioResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    /*@GET("usuarios/usuario/{id}")
    fun obtenerUsuarioPorId(@Path("id") id: Int): Call<String>*/
    @FormUrlEncoded
    @POST("usuarios/login")
    fun login(
        @Field("usuarioOEmail") usuarioOEmail: String,
        @Field("clave") clave: String
    ): Call<UsuarioResponse>

}