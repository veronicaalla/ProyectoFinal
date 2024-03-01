package es.veronica.alvarez.omega.CreateUser

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentBirthRegistrationBinding
import java.util.Calendar


class BirthRegistrationFragment : Fragment() {

    lateinit var binding: FragmentBirthRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBirthRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén la fecha actual
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        //La aplicacion solo van a poder obtenerla todas aquellas que
        //Tengan una edad minima de 12 años
        // Resta 12 años a la fecha actual
        calendar.set(currentYear - 12, currentMonth, currentDay)

        // Configuramos la fecha con la fecha mínima
        binding.fechaNacimiento.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            }
        )

        // Configura la fecha mínima en el DatePicker
        binding.fechaNacimiento.maxDate = calendar.timeInMillis
        //Damos las transiciones
        binding.btnContinuar.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_birthRegistrationFragment_to_usernameRegistrationFragment)

        }
    }


}