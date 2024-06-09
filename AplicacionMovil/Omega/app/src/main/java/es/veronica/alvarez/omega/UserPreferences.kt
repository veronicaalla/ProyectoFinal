package es.veronica.alvarez.omega

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import es.veronica.alvarez.omega.Model.GeneroResponse

class UserPreferences (context : Context) {

    private val prefsName = "UserPreferences"
    private val isLoggedInKey = "isLoggedIn"

    private val userIdKey = "idUsuario"
    private val username = "usuario"
    private val privacidad = "publico"

    private val generosFavoritosKey = "generosFavoritos"

    /*MutableLiveData for list of GeneroResponse
    private val _lista = MutableLiveData<List<GeneroResponse>>()
    val lista: LiveData<List<GeneroResponse>> = _lista
    // Example function to update the list
    fun setLista(generos: List<GeneroResponse>) {
        _lista.value = generos
    }*/


    private val prefs: SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    var isLoggedIn:Boolean
        get() = prefs.getBoolean(isLoggedInKey, false)
        set(value) = prefs.edit().putBoolean(isLoggedInKey, value).apply()

    var userId: Int
        get() = prefs.getInt(userIdKey, -1)
        set(value) = prefs.edit().putInt(userIdKey, value).apply()

    var _username: String?
        get() = prefs.getString(username, "")
        set(value) = prefs.edit().putString(username, value).apply()

    var _privacidad : Boolean
        get() = prefs.getBoolean(privacidad, false)
        set(value) = prefs.edit().putBoolean(privacidad, value).apply()

    var generosFavoritos: Set<Int>
        get() {
            val generosFavoritosSet = prefs.getStringSet(generosFavoritosKey, setOf()) ?: setOf()
            return generosFavoritosSet.mapNotNull { it.toIntOrNull() }.toSet()
        }
        set(value) {
            val generosFavoritosSet = value.map { it.toString() }.toSet()
            prefs.edit().putStringSet(generosFavoritosKey, generosFavoritosSet).apply()
        }


    // Funciones para la lista de GeneroResponse
    var lista: List<GeneroResponse>
        get() {
            val jsonString = prefs.getString("listaGeneros", "[]")
            return if (jsonString.isNullOrEmpty()) {
                emptyList()
            } else {
                Gson().fromJson(jsonString, Array<GeneroResponse>::class.java).toList()
            }
        }
        set(value) {
            val jsonString = Gson().toJson(value)
            prefs.edit().putString("listaGeneros", jsonString).apply()
        }

}