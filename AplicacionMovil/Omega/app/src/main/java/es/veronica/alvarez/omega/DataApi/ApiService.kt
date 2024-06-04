package es.veronica.alvarez.omega.DataApi

import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.LibrosBibliotecaResponse
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

/**
 * Interfaz para interactuar con la API de Omega.
 */
interface ApiService {
    /*@GET("usuarios/usuario/{id}")
    fun obtenerUsuarioPorId(@Path("id") id: Int): Call<String>*/

    /**
     * Realiza el login de un usuario.
     *
     * @param usuarioOEmail El usuario o email del usuario.
     * @param clave La clave del usuario.
     * @return Una respuesta con el usuario autenticado.
     */
    @FormUrlEncoded
    @POST("usuarios/login")
    fun login(
        @Field("usuarioOEmail") usuarioOEmail: String,
        @Field("clave") clave: String
    ): Call<UsuarioResponse>

    /**
     * Obtiene el perfil de un usuario por su ID.
     *
     * @param idUsuario El ID del usuario.
     * @return Una respuesta con el perfil del usuario.
     */
    @GET("perfiles/usuario/{idUsuario}")
    fun obtenerPerfilPorIdUsuario(@Path("idUsuario") idUsuario: Int): Call<PerfilUsuarioResponse>


    /**
     * Modifica el perfil de un usuario.
     *
     * @param idUsuario El ID del usuario.
     * @param perfilUsuarioNuevo El perfil del usuario con los cambios.
     * @return Una respuesta vacía si se modificó correctamente.
     */
    @PUT("perfiles/modificar/{idUsuario}")
    fun modificarPerfil(
        @Path("idUsuario") idUsuario: Int,
        @Body perfilUsuarioNuevo: PerfilUsuarioResponse
    ): Call<Any>


    /**
     * Obtiene las bibliotecas de un usuario.
     *
     * @param usuarioId El ID del usuario.
     * @return Una lista de bibliotecas del usuario.
     */
    @GET("bibliotecas/usuario/{usuarioId}")
    fun obtenerBibliotecas(@Path("usuarioId") usuarioId: Int): Call<List<BibliotecaResponse>>


    /**
     * Obtiene los libros de una biblioteca.
     *
     * @param bibliotecaId El ID de la biblioteca.
     * @return Una lista de libros de la biblioteca.
     */
    @GET("librosBiblioteca/biblioteca/{bibliotecaId}")
    fun obtenerLibrosPorBiblioteca(@Path("bibliotecaId") bibliotecaId: Int): Call<List<LibrosBibliotecaResponse>>


    /**
     * Obtiene los géneros de un usuario.
     *
     * @param idUsuario El ID del usuario.
     * @return Una lista de géneros del usuario.
     */
    @GET("generosUsuario/{idUsuario}")
    fun obtenerGenerosPorUsuario(@Path("idUsuario") idUsuario: Int): Call<List<GeneroUsuarioResponse>>

     /**
     * Obtiene una lista de libros aleatorios.
     *
     * @return Una lista de libros aleatorios.
     */
    @GET("libros/librosaleatorios")
    fun obtenerLibros(): Call<List<LibroResponse>>

    /**
     * Obtiene la valoración de un libro.
     *
     * @param idLibro El ID del libro.
     * @return La valoración del libro.
     */
    @GET("valoracionesLibros/valoracion/{idLibro}")
    fun obtenerValoracionLibro(@Path("idLibro") idLibro: Int): Call<Double>

    /**
     * Obtiene los libros de un género.
     *
     * @param generoId El ID del género.
     * @return Una lista de libros del género.
     */
    @GET("libros/genero/{generoId}")
    fun obtenerLibrosPorGenero(@Path("generoId") generoId: Int): Call<List<LibroResponse>>
}
