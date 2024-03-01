package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentRegistrationGenderBinding
import es.veronica.alvarez.omega.databinding.FragmentRegistrationNameBinding


class RegistrationGenderFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationGenderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSiguiente.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_registrationGenderFragment_to_birthRegistrationFragment)

        }
    }

}