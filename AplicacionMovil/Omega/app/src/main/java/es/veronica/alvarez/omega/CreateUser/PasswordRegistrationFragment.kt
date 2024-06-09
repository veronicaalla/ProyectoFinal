package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentPasswordRegistrationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRegistrationBinding
    private val viewModelCompartido: UsuarioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPasswordRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFinalizar.setOnClickListener {
            if (validarDatos()) {
                if (verificarPassword()) {
                    //Llamamos al método de la api
                    crearUsuario()
                }
            }
        }
    }

    private fun crearUsuario() {
        //Llamamos al método de la api
        val usuario = UsuarioResponse(
            id = null,
            nombre = viewModelCompartido.nombre.value,
            apellidos = viewModelCompartido.apellidos.value,
            correo = viewModelCompartido.email.value,
            alias = viewModelCompartido.username.value,
            clave = viewModelCompartido.password.value,
        )

        context?.let { Api.initialize(it.applicationContext) }
        //Llamamos al metodo api
        Api.retrofitService.crearUsuario(usuario).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
               if (response.isSuccessful){
                   Log.i("Succesful", response.body().toString())

                     }else{
                   Log.i("noSucces", response.message().toString())
               }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }

        })



        usuario.alias?.let { existeUsuario(it) }



    }

    private fun existeUsuario(alias: String) {
        // Crear un Handler
        val handler = Handler(Looper.getMainLooper())

        // Debemos esperar a que se cree el usuario
        handler.postDelayed({
            // El código dentro de esta función se ejecutará después de 10 segundos
            context?.let { Api.initialize(it.applicationContext) }
            Api.retrofitService.buscarUsuarioPorUser(alias).enqueue(object : Callback<UsuarioResponse> {
                override fun onResponse(call: Call<UsuarioResponse>, response: Response<UsuarioResponse>) {
                    val usuario = response.body()
                    Log.d("API Response", "Usuario: $usuario")
                    Toast.makeText(requireContext(), "Usuario creado correctamente", Toast.LENGTH_SHORT).show()

                    //Le asignamos el id al userPreferences
                    var userPreferences = UserPreferences(requireContext())
                    if (usuario != null) {
                        userPreferences.userId = usuario.id!!
                        userPreferences._username = usuario.alias
                        userPreferences._privacidad = usuario.publico
                    }


                    // Navegar a la siguiente pantalla si es necesario
                    view?.findNavController()?.navigate(R.id.action_passwordRegistrationFragment_to_literarySelectionFragment)
                }

                override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error al crear el usuario", Toast.LENGTH_SHORT).show()
                }
            })
        }, 10000) // 10000 ms = 10 segundos
    }


    private fun verificarPassword(): Boolean {
        //Comprobamos que las contraseñas sean iguales
        if (binding.txtPassword.text.contentEquals(binding.txtPassword2.text)) {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            viewModelCompartido.setUsename(username)
            viewModelCompartido.setPassword(password)

        } else {
            Toast.makeText(context, "Las contraseñas no son iguales", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun validarDatos(): Boolean {

        if (binding.txtUsername.text.isEmpty()) {
            Toast.makeText(context, "Introduce tu nombre de usuario", Toast.LENGTH_SHORT).show()
            binding.txtUsername.focusable
            return false
        }

        if (binding.txtPassword.text.isEmpty()) {
            Toast.makeText(context, "Introduce la contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.txtPassword2.text.isEmpty()) {
            Toast.makeText(context, "Introduce la contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}

