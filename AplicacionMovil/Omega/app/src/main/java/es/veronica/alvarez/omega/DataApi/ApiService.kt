package es.veronica.alvarez.omega.DataApi

import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("perfiles/usuario/{idUsuario}")
    fun obtenerPerfilPorIdUsuario(@Path("idUsuario") idUsuario: Int): Call<PerfilUsuarioResponse>

    @PUT("perfiles/modificar/{idUsuario}")
    fun modificarPerfil(
        @Path("idUsuario") idUsuario: Int,
        @Body perfilUsuarioNuevo: PerfilUsuarioResponse
    ): Call<Any>

    @GET("bibliotecas/usuario/{usuarioId}")
    fun obtenerBibliotecas(@Path("usuarioId") usuarioId: Int): Call<List<BibliotecaResponse>>

    @GET("generosUsuario/{idUsuario}")
    fun obtenerGenerosPorUsuario(@Path("idUsuario") idUsuario: Int): Call<List<GeneroUsuarioResponse>>

    //Metodos para buscar
    @GET("libros/librosaleatorios")
    fun obtenerLibros(): Call<List<LibroResponse>>

    @GET("valoracionesLibros/valoracion/{idLibro}")
    fun obtenerValoracionLibro(@Path("idLibro") idLibro: Int): Call<Double>

    @GET("libros/genero/{generoId}")
    fun obtenerLibrosPorGenero(@Path("generoId") generoId: Int): Call<List<LibroResponse>>
}