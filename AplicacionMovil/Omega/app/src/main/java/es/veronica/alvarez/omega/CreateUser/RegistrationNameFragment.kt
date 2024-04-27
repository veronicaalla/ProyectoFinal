package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
                //Si los datos son validos seguimos con el registro
                view.findNavController()
                    .navigate(R.id.action_registrationNameFragment_to_passwordRegistrationFragment)
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

        if (binding.txtFechaNacimiento.text.isEmpty()){
            Toast.makeText(context, "Introduce la fecha de nacimiento", Toast.LENGTH_LONG).show()
        }

        if (binding.txtEmail.text.isEmpty()){
            Toast.makeText(context, "Introduce su email", Toast.LENGTH_LONG).show()
        }

        //Verificamos que el correo sea valido
        if (verificarCorreo(binding.txtEmail.text)){
            Toast.makeText(context, "Introduce un email valido", Toast.LENGTH_LONG).show()

        }
        return true
    }

    private fun verificarCorreo(correo: Editable):Boolean{
        val regex = "^[\\w-\\.]+@([\\w-]+\\.)+(com|es)\$"
        return correo.matches(regex.toRegex())
    }

    private fun mensajeError(mensaje: String) {
        val dialog = context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("ERROR")
                .setMessage(mensaje)

                //Accion de okey
                .setNegativeButton("Ok", null)
                .create()
        }
        dialog?.show()
    }

}