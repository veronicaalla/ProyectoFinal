package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentRegistrationNameBinding


class RegistrationNameFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegistrationNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSiguiente.setOnClickListener {

            //Validamos que los datos no esten vacios
            if (Validar()) {
                view.findNavController()
                    .navigate(R.id.action_registrationNameFragment_to_registrationGenderFragment)

            }
        }

    }

    private fun Validar(): Boolean {
        if (binding.txtNombre.text.isEmpty()) {
            Toast.makeText(context, "Introduce el nombre", Toast.LENGTH_LONG).show()
            return false
        }
        if (binding.txtApellidos.text.isEmpty()) {
            Toast.makeText(context, "Introduce los apellidos", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}