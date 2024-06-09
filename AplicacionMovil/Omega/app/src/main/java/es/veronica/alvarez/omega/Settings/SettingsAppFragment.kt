package es.veronica.alvarez.omega.Settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentEditProfileBinding
import es.veronica.alvarez.omega.databinding.FragmentSettingsAppBinding


class SettingsAppFragment : Fragment() {

    private lateinit var binding: FragmentSettingsAppBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsAppBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.filaPrivacidad.setOnClickListener {
            view.findNavController().navigate(R.id.action_settingsAppFragment_to_accountPrivacyFragment)
        }

        binding.filaInformacionCuenta.setOnClickListener {
            view.findNavController().navigate(R.id.action_settingsAppFragment_to_informationAccountFragment)
        }

        binding.filaDatosPersonales.setOnClickListener {
            view.findNavController().navigate(R.id.action_settingsAppFragment_to_personalDetailsFragment)
        }

        binding.filaPasswordSecurity.visibility = View.GONE

        binding.filaCerrarSesion.setOnClickListener{
            //A la hora de cerrar sesion, debemos eliminar todos los datos del usuario
            var userPreferences = UserPreferences(requireContext())
            //Modificamos los datos
            userPreferences.userId = -1
            userPreferences._privacidad = false
            userPreferences.isLoggedIn = false

            view.findNavController().navigate(R.id.action_settingsAppFragment_to_logginFragment)
        }

    }
}