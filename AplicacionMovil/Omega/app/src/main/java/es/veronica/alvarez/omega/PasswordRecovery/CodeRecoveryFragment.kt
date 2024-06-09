package es.veronica.alvarez.omega.PasswordRecovery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentCodeRecoveryBinding
import es.veronica.alvarez.omega.databinding.FragmentPasswordRecoveryBinding

class CodeRecoveryFragment : Fragment() {

    private lateinit var binding: FragmentCodeRecoveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeRecoveryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinuar.setOnClickListener {
            //Comprobamos que el campo no este vacio
            if (binding.editTextCodigo.text.isNotEmpty()) {
                //Comprobamos que el codigo de recuperacion sea el mismo que el guardado en la db
                view.findNavController()
                    .navigate(R.id.action_codeRecoveryFragment_to_newPasswordFragment)

            } else {
                Toast.makeText(context, "Introduce el codigo", Toast.LENGTH_LONG).show()
            }
        }

        binding.txtCodigoNoRecibido.setOnClickListener {
            Toast.makeText(context, "El código se ha vuelto a enviar", Toast.LENGTH_LONG).show()
        }
    }

}