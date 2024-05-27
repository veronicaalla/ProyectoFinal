package es.veronica.alvarez.omega.DataApi

import es.veronica.alvarez.omega.Model.BookResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /*@GET("usuarios/usuario/{id}")
    fun obtenerUsuarioPorId(@Path("id") id: Int): Call<String>*/
    @FormUrlEncoded
    @POST("usuarios/login")
    fun login(
        @Field("usuarioOEmail") usuarioOEmail: String,
        @Field("clave") clave: String
    ): Call<UsuarioResponse>

    @GET("generosUsuario/{idUsuario}")
    fun obtenerGenerosPorUsuario(@Path("idUsuario") idUsuario: Int): Call<List<GeneroUsuarioResponse>>

    //Metodos para buscar
    @GET("libros/librosaleatorios")
    fun obtenerLibros(): Call<List<BookResponse>>

    @GET("valoracionesLibros/valoracion/{idLibro}")
    fun obtenerValoracionLibro(@Path("idLibro") idLibro: Int): Call<Double>
}