package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.databinding.FragmentLogginBinding
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.UsuarioResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class LogginFragment : Fragment() {

    private lateinit var binding: FragmentLogginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Comprobamos si el usuario esta logeado
        var userPreferences = UserPreferences(requireContext())
        if (userPreferences.isLoggedIn){
            //Navegamos hasta la página principal
            view?.findNavController()
                ?.navigate(R.id.action_logginFragment_to_startAppFragment)
        }

        //------------------- INICIO DE SESIÓN ------------------
        binding.btnIniciarSesion.setOnClickListener {
            if (ValidarDatos()) {
                comprobarCredenciales()
            }

        }


        //region CONTRASEÑA OLVIDADA
        binding.txtOlvidoPassword.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_logginFragment_to_passwordRecoveryFragment)
        }

        //endregion


        //region CREAR CUENETA
        binding.txtRegistrarse.setOnClickListener {
            view.findNavController().navigate(R.id.action_logginFragment_to_joinFragment)
        }

        //endregion

    }

    private fun comprobarCredenciales() {
        //Obtenemos los datos
        val usuario = binding.txtNombreUsuario.text.toString()
        Log.i("Usuario", usuario)
        val password = binding.txtPassword.text.toString()
        Log.i("Contraseña", password)

        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            //Le enviamos el token
            Api.retrofitService.login(usuario, password)
                .enqueue(object : Callback<UsuarioResponse> {
                    override fun onResponse(
                        call: Call<UsuarioResponse>,
                        response: Response<UsuarioResponse>
                    ) {

                        if (response.isSuccessful) {
                            Log.i("Login succesful", response.body().toString());
                            //Debemos comprobar que el usuario no sea administrador
                            var tipoUsuario = response.body()?.tipo

                            if (tipoUsuario == 3 || tipoUsuario == 4) {
                                almacenamosUsuario(response.body())
                                //Navegamos hasta la página principal
                                view?.findNavController()
                                    ?.navigate(R.id.action_logginFragment_to_startAppFragment)
                            } else {
                                MensajeValidaciones("No tienes acceso, para iniciar, registrese como usuario")
                            }

                        }
                    }

                    override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                        Log.i("Error login", t.toString())
                        Log.i("error", t.printStackTrace().toString())
                    }
                })
        }

    }

    private fun almacenamosUsuario(usuario: UsuarioResponse?) {
        val userPreferences = UserPreferences(requireContext())

        if (usuario != null) {
            userPreferences.isLoggedIn = true
            userPreferences.userId = usuario.id!!
            userPreferences._username = usuario.alias
            userPreferences._privacidad = usuario.publico!!
        }
    }

    private fun ValidarDatos(): Boolean {
        val usuario = binding.txtNombreUsuario.text
        val password = binding.txtPassword.text

        //Comprobamos que los datos no esten vacios
        if (usuario.isEmpty()) {
            MensajeValidaciones("Por favor, introduzca el usuario")
            return false
        }

        if (password.isEmpty()) {
            MensajeValidaciones("Por favor, introduzca la contraseña")
            return false
        }

        return true
    }


    private fun MensajeValidaciones(mensaje: String) {
        // Creamos la ventana emergente
        val dialog = context?.let {
            MaterialAlertDialogBuilder(it).setTitle("ERROR").setMessage(mensaje)
                .setNegativeButton("OK", null).create()

        }
        if (dialog != null) {
            dialog.show()
        }
    }

}