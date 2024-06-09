package es.veronica.alvarez.omega.DataApi

import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.LibrosBibliotecaResponse
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.Model.ValoracionUsuarioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz para interactuar con la API de Omega.
 */
interface ApiService {


    //region Usuario

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario que se va a buscar.
     * @return Call<UsuarioResponse> Una llamada que devuelve un objeto de tipo UsuarioResponse.
     */
    @GET("usuarios/usuario/{id}")
    fun obtenerUsuarioPorId(@Path("id") id: Int): Call<UsuarioResponse>


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
     * Busca un usuario por su nombre de usuario.
     *
     * @param user El nombre de usuario del usuario que se va a buscar.
     * @return Call<UsuarioResponse> Una llamada que devuelve un objeto de tipo UsuarioResponse.
     */
    @GET("usuarios/usuarios/{user}")
    fun buscarUsuarioPorUser(@Path("user") user: String): Call<UsuarioResponse>


    /**
     * Crea un nuevo usuario.
     *
     * @param usuario El usuario que se va a crear.
     * @return Call<String> Una llamada que devuelve un objeto de tipo String.
     */
    @POST("usuarios/crearUsuario")
    fun crearUsuario(@Body usuario: UsuarioResponse): Call<String>


    //endregion


    //region Perfil usuario

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


    //endregion


    //region Bibliotecas

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
     * Crea una nueva biblioteca para un usuario específico.
     *
     * @param usuarioId El ID del usuario para el cual se creará la biblioteca.
     * @param biblioteca La biblioteca que se va a crear.
     * @return Call<BibliotecaResponse> Una llamada que devuelve un objeto de tipo BibliotecaResponse.
     */
    @POST("bibliotecas/usuario/{usuarioId}/biblioteca")
    fun crearBiblioteca(@Path("usuarioId") usuarioId: Int, @Body biblioteca: BibliotecaResponse): Call<BibliotecaResponse>

    //endregion


    //region Géneros
    /**
     * Obtiene los géneros de un usuario.
     *
     * @param idUsuario El ID del usuario.
     * @return Una lista de géneros del usuario.
     */
    @GET("generosUsuario/{idUsuario}")
    fun obtenerGenerosPorUsuario(@Path("idUsuario") idUsuario: Int): Call<List<GeneroUsuarioResponse>>


    /**
     * Obtiene un género por su ID.
     *
     * @param id El ID del género que se va a buscar.
     * @return Call<GeneroResponse> Una llamada que devuelve un objeto de tipo GeneroResponse.
     */
    @GET("generos/id/{id}")
    fun obtenerGeneroPorId(@Path("id") id: Int): Call<GeneroResponse>



    /**
     * Obtiene todos los géneros disponibles.
     *
     * @return Call<List<GeneroResponse>> Una llamada que devuelve una lista de objetos de tipo GeneroResponse.
     */
    @GET("generos/")
    fun obtenerGeneros(): Call<List<GeneroResponse>>



    /**
     * Asocia varios géneros a un usuario.
     *
     * @param idUsuario El ID del usuario al cual se van a asociar los géneros.
     * @param idGeneros Una lista de IDs de géneros que se van a asociar al usuario.
     * @return Call<Void> Una llamada que no devuelve ningún objeto en la respuesta.
     */
    @POST("generosUsuario/asociarGeneros")
    fun asociarGenerosAUsuario(
        @Query("idUsuario") idUsuario: Int,
        @Query("idGeneros") idGeneros: List<Int>
    ): Call<Void>

    //endregion


    //region Libros

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


    /**
     * Obtiene todos los libros para realizar su filtrado por busqueda
     */
    @GET("libros")
    fun obtenerTotalLibros(): Call<List<LibroResponse>>


    /**
     * Crea un nuevo libro asociado a un usuario.
     *
     * @param usuarioId El ID del usuario al cual se va a asociar el nuevo libro.
     * @param nuevoLibro El nuevo libro que se va a crear.
     * @return Call<LibroResponse> Una llamada que devuelve un objeto de tipo LibroResponse.
     */
    @POST("libros/usuarios/{usuarioId}/libros")
    fun crearLibro(@Path("usuarioId") usuarioId: Int, @Body nuevoLibro: LibroResponse): Call<LibroResponse>


    //endregion


    //region Libro Erroneo

    /**
     * Crea un nuevo libro erróneo.
     *
     * @param idLibro El ID del libro erróneo que se va a crear.
     * @param idReportante El ID del usuario que reporta el libro erróneo.
     * @return Call<Void> Una llamada que no devuelve ningún objeto en la respuesta.
     */
    @FormUrlEncoded
    @POST("libroerroneno/crear")
    fun crearLibroErroneo(
        @Field("idLibro") idLibro: Int,
        @Field("idReportante") idReportante: Int
    ): Call<Void>

    //endregion



    //region Comentarios

    /**
     * Obtiene los comentarios asociados a un libro por su ID.
     *
     * @param idLibro El ID del libro del cual se van a obtener los comentarios.
     * @return Call<List<ComentarioResponse>> Una llamada que devuelve una lista de objetos de tipo ComentarioResponse.
     */
    @GET("comentarios/libro/{idLibro}")
    fun obtenerComentariosPorLibro(@Path("idLibro") idLibro: Int): Call<List<ComentarioResponse>>


    /**
     * Crea un nuevo comentario para un libro.
     *
     * @param idLibro El ID del libro al cual se va a asociar el comentario.
     * @param idUsuario El ID del usuario que realiza el comentario.
     * @param comentario El contenido del comentario.
     * @return Call<ComentarioResponse> Una llamada que devuelve un objeto de tipo ComentarioResponse.
     */
    @FormUrlEncoded
    @POST("comentarios/crear")
    fun crearComentario(
        @Field("idLibro") idLibro: Int,
        @Field("idUsuario") idUsuario: Int,
        @Field("comentario") comentario: String
    ): Call<ComentarioResponse>

    //endregion


    //region Comentarios Reportados

    /**
     * Reporta un comentario.
     *
     * @param comentarioId El ID del comentario que se va a reportar.
     * @param usuarioId El ID del usuario que reporta el comentario.
     * @return Call<Void> Una llamada que no devuelve ningún objeto en la respuesta.
     */
    @FormUrlEncoded
    @POST("comentarioreportado/crear")
    fun reportarComentario(
        @Field("comentarioId") comentarioId: Int,
        @Field("usuarioId") usuarioId: Int
    ): Call<Void>

    //endregion


    //region Ajustes Usuarios

    /**
     * Modifica la configuración de privacidad de un usuario.
     *
     * @param idUsuario El ID del usuario cuya privacidad se va a modificar.
     * @param privacidad El nuevo estado de privacidad del usuario.
     * @return Call<UsuarioResponse> Una llamada que devuelve un objeto de tipo UsuarioResponse.
     */
    @PUT("usuarios/privacidad/{idUsuario}")
    fun modificarPrivacidad(
        @Path("idUsuario") idUsuario: Int,
        @Query("publico") privacidad: Boolean
    ): Call<UsuarioResponse>

    //endregion


    //region Valoraciones Usuario

    /**
     * Obtiene la valoración de un libro realizada por un usuario específico.
     *
     * @param idLibro El ID del libro del cual se quiere obtener la valoración.
     * @param idUsuario El ID del usuario que realizó la valoración.
     * @return Call<ValoracionUsuarioResponse> Una llamada que devuelve un objeto de tipo ValoracionUsuarioResponse.
     */
    @GET("valoracionesUsuarios/libro/{idLibro}/usuario/{idUsuario}")
    fun obtenerValoracionLibroPorUsuario(
        @Path("idLibro") idLibro: Int,
        @Path("idUsuario") idUsuario: Int
    ): Call<ValoracionUsuarioResponse>


    /**
     * Crea una nueva valoración de un usuario para un libro.
     *
     * @param idUsuario El ID del usuario que realiza la valoración.
     * @param idLibro El ID del libro que es valorado por el usuario.
     * @param valoracionUsuario La valoración del usuario para el libro.
     * @return Call<ValoracionUsuarioResponse> Una llamada que devuelve un objeto de tipo ValoracionUsuarioResponse.
     */
    @POST("valoracionesUsuarios/usuarios/{idUsuario}/libros/{idLibro}/valoraciones")
    fun crearValoracionUsuario(
        @Path("idUsuario") idUsuario: Int,
        @Path("idLibro") idLibro: Int,
        @Body valoracionUsuario: ValoracionUsuarioResponse
    ): Call<ValoracionUsuarioResponse>


    /**
     * Actualiza la valoración de un usuario para un libro.
     *
     * @param idUsuario El ID del usuario cuya valoración se va a actualizar.
     * @param idLibro El ID del libro cuya valoración se va a actualizar.
     * @param valoracionUsuario La nueva valoración del usuario para el libro.
     * @return Call<Any> Una llamada que no devuelve ningún objeto en la respuesta.
     */
    @PUT("valoracionesUsuarios/actualizar/{idUsuario}/{idLibro}")
    fun actualizarValoracion(
        @Path("idUsuario") idUsuario: Int,
        @Path("idLibro") idLibro: Int,
        @Body valoracionUsuario: ValoracionUsuarioResponse
    ): Call<Any>

    //endregion

}
