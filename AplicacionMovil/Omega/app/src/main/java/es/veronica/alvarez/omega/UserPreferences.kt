package es.veronica.alvarez.omega

import android.content.Context
import android.content.SharedPreferences

class UserPreferences (context : Context) {

    private val prefsName = "UserPreferences"
    private val isLoggedInKey = "isLoggedIn"

    private val userIdKey = "idUsuario"
    private val username = "usuario"

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
}