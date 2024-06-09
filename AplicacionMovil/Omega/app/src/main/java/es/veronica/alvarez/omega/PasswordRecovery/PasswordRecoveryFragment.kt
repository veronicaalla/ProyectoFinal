package es.veronica.alvarez.omega.PasswordRecovery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentLogginBinding
import es.veronica.alvarez.omega.databinding.FragmentPasswordRecoveryBinding

class PasswordRecoveryFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRecoveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordRecoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuscarCuenta.setOnClickListener {

            //Comprobamos que el campo no este vacio
            if (binding.editTextCorreo.text.isNotEmpty()) {

                //Realizamos una llamada a la api que compruebe que el correo electronico
                //esta asignado en la base de datos

                val existe = true;
                if (existe) {
                    //Si existe, nos lleva a otra ventana
                    view.findNavController()
                        .navigate(R.id.action_passwordRecoveryFragment_to_codeRecoveryFragment)
                }
            } else {
                Toast.makeText(context, "El correo electronico esta vacio", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


}