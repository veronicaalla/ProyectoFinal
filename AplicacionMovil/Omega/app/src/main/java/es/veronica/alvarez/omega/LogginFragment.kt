package es.veronica.alvarez.omega

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.databinding.FragmentLogginBinding


class LogginFragment : Fragment() {

    private lateinit var binding: FragmentLogginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_loggin, container, false)
        binding = FragmentLogginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //------------------- INICIO DE SESIÓN ------------------
        binding.btnIniciarSesion.setOnClickListener {
            //Comprobamos que los campos no esten vacios
            if (binding.txtNombreUsuario.text.isNotEmpty()) {
                if (binding.txtPassword.text.isNotEmpty()) {

                    //Almacenamos las variables
                    val usuario = binding.txtNombreUsuario.text
                    val clave = binding.txtPassword.text

                    //Comprobamos si el usuario ha introducido un correo o el numero de telefono
                    if (usuario.contains('@')) {
                        //Método api que comprueba por correo
                    } else {
                        //Método api que comprueba por telefono
                    }


                    view.findNavController()
                        .navigate(R.id.action_logginFragment_to_startAppFragment)
                } else {
                    Toast.makeText(context, "Introduce la contraseña", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Introduce el nombre", Toast.LENGTH_LONG).show()
            }

        }

        //------------------- SE HA OLVIDADO LA CONTRASEÑA ------------------
        binding.txtOlvidoPassword.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_logginFragment_to_passwordRecoveryFragment)
        }


        //------------------- CREAR CUENTA ------------------
        binding.btnCrearCuenta.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_logginFragment_to_joinFragment)
        }
    }
}