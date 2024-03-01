package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentCodeRecoveryBinding
import es.veronica.alvarez.omega.databinding.FragmentJoinBinding

class JoinFragment : Fragment() {

    lateinit var binding: FragmentJoinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEmpezar.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_joinFragment_to_registrationNameFragment)
        }

        binding.btnCuentaCreada.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_joinFragment_to_logginFragment)
        }

    }
}