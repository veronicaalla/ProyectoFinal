package es.veronica.alvarez.omega.DataApi

import android.content.Context
import android.content.res.Resources
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.Properties

class RetrofitBuilder {

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
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
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

    fun build(context: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(getApiEndpoint(context))
            .addConverterFactory(StringConverterFactory.create())
            .build()
}


