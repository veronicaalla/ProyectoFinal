package es.veronica.alvarez.omega.DataApi

import android.content.Context
import android.content.res.Resources
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.Properties


class RetrofitBuilder {


    /**
     * Obtiene el endpoint de la API desde el archivo api.properties en la carpeta res/raw.
     *
     * @param context El contexto de la aplicación.
     * @return String El endpoint de la API.
     * @throws Resources.NotFoundException Si el archivo api.properties no se encuentra en la carpeta res/raw.
     * @throws IllegalStateException Si la propiedad 'Api.endpoint' no está definida en el archivo api.properties.
     */
    fun getApiEndpoint(context: Context): String {
        val resourceId = context.resources.getIdentifier("api", "raw", context.packageName)
        if (resourceId == 0) {
            throw Resources.NotFoundException("El archivo api.properties no se pudo encontrar en la carpeta res/raw")
        }

        val rawResource = context.resources.openRawResource(resourceId)

        val properties = Properties()
        properties.load(rawResource)
        return properties.getProperty("Api.endpoint")
            ?: throw IllegalStateException("La propiedad 'Api.endpoint' no está definida en el archivo api.properties")
    }

    // Crea una implementación de Converter.Factory para manejar el string directamente
    class StringConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(type: Type,annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            if (type == String::class.java) {
                return Converter { value -> value.string() }
            }
            return null
        }

        companion object {
            fun create(): StringConverterFactory {
                return StringConverterFactory()
            }
        }
    }

    //StringConverterFactory.create()
    val gson = GsonBuilder().create()
    fun build(context: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(getApiEndpoint(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}


