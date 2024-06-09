package es.veronica.alvarez.omega.CreateUser

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentRegistrationNameBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class RegistrationNameFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationNameBinding
    private val calendar = Calendar.getInstance()
    private val viewModelCompartido: UsuarioViewModel by activityViewModels()

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
                
                //Si los datos son validos, se los pasamos al view model
                viewModelCompartido.setNombre(binding.txtNombre.text.toString())
                viewModelCompartido.setApellidos(binding.txtApellidos.text.toString())
                viewModelCompartido.setEmail(binding.txtEmail.text.toString())

                // seguimos con el registro
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


        var email = binding.txtEmail.text.toString()
        if (email.isEmpty()) {
            Toast.makeText(context, "Introduce el email", Toast.LENGTH_SHORT).show()
            return false
        } else {
            if (!isValidEmail(email)) {
                Toast.makeText(context, "Introduce un email valido", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    private fun isValidEmail(email:String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|es|org)")
        return emailRegex.matches(email)
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