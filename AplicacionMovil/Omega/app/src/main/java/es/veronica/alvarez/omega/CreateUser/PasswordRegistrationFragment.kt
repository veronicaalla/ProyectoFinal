package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentPasswordRegistrationBinding

class PasswordRegistrationFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_password_registration, container, false)
        binding = FragmentPasswordRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val password = binding.txtPassword.text
        val password2 = binding.txtPassword2.text

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

        //Si todo ha salido bien
        view?.findNavController()
            ?.navigate(R.id.action_passwordRegistrationFragment_to_literarySelectionFragment)
    }


    private fun verificarPassword(): Boolean {

        if (binding.txtPassword.text.contentEquals(binding.txtPassword2.text)) {
            //si las contraseñas son iguales
            //Comprobamos que cumplan con la seguridad adecuada
            /*if (isValidPassword(binding.txtPassword.text)) {
                return true
            }*/

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

    /*fun isValidPassword(password: Editable): Boolean {
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}\\]:;',?/*~$^+=<>]).{8,20}$"
        return password.matches(regex.toRegex())
    }*/