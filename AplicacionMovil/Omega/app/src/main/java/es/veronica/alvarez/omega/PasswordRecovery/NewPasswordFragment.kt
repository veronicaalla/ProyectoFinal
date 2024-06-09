package es.veronica.alvarez.omega.PasswordRecovery

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentCodeRecoveryBinding
import es.veronica.alvarez.omega.databinding.FragmentNewPasswordBinding
import java.math.BigInteger
import java.security.MessageDigest


class NewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnFinalizar.setOnClickListener {

            //Comprobamos que ambos campos no esten vacios
            if (binding.editTxtPassword.text.isNotEmpty() || binding.editTxtPassword2.text.isEmpty()) {

                if (binding.editTxtPassword.text.contentEquals(binding.editTxtPassword2.text)) {

                    //La encriptación de la contraseña se realiza desde el método api
                    val clave = binding.editTxtPassword2.text.toString()
                    view.findNavController()
                        .navigate(R.id.action_newPasswordFragment_to_logginFragment)
                } else {
                    Toast.makeText(context, "Las contraseñas no son iguales", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

    }


}