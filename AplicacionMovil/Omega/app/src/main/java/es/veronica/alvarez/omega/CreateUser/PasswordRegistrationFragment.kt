package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
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

        binding.btnFinalizar.setOnClickListener {

            //Comprobamos que ambos campos no esten vacios
            if (binding.editTxtPassword.text.isNotEmpty() || binding.editTxtPassword2.text.isEmpty()) {

                if (binding.editTxtPassword.text.contentEquals(binding.editTxtPassword2.text)) {
                    //Hacemos una llamada a la Api y actualizamos la nueva contraseña

                    view.findNavController()
                        .navigate(R.id.action_passwordRegistrationFragment_to_literarySelectionFragment)
                } else {
                    Toast.makeText(context, "Las contraseñas no son iguales", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

}