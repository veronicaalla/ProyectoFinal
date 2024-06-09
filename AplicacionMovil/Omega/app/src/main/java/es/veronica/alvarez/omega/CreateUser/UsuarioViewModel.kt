package es.veronica.alvarez.omega.CreateUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.veronica.alvarez.omega.Model.GeneroResponse
import java.time.LocalDate

class UsuarioViewModel : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    private val _apellidos = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _username = MutableLiveData<String>()



    val nombre: LiveData<String> = _nombre
    val apellidos: LiveData<String> = _apellidos
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val username: LiveData<String> = _username


    fun setUsename(tipo: String) {
        _username.value = tipo
    }

    fun setNombre(tipo: String) {
        _nombre.value = tipo
    }
    fun setApellidos(tipo: String) {
        _apellidos.value = tipo
    }
    fun setEmail(tipo: String) {
        _email.value = tipo
    }
    fun setPassword(tipo: String) {
        _password.value = tipo
    }

    fun getUsername():String{
        return _username.toString()
    }


}
