package es.veronica.alvarez.omega.DataApi

import android.content.Context
import retrofit2.Retrofit

/**
 * Objeto Api que se utiliza para inicializar y obtener una instancia de ApiService
 */
object Api {
    /**
     * Propiedad privada que almacenará la instancia de Retrofit
     */
    private lateinit var retrofit: Retrofit


     /**
     * Propiedad que almacenará la instancia de ApiService
     */
    lateinit var retrofitService: ApiService

    /**
     * Método que se utiliza para inicializar la instancia de Retrofit y crear una instancia de ApiService
     * @param context Contexto de la aplicación
     */
    fun initialize(context: Context) {
        /**
         * Crear una instancia de Retrofit usando el constructor predeterminado de RetrofitBuilder
         * y el contexto de la aplicación
         */
        retrofit = RetrofitBuilder().build(context.applicationContext)

        /**
         * Crear una instancia de ApiService usando la instancia de Retrofit recién creada
         */
        retrofitService = retrofit.create(ApiService::class.java)
    }


}
