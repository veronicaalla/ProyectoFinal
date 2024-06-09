package es.veronica.alvarez.omega.Settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentAccountPrivacyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountPrivacyFragment : Fragment() {

    private lateinit var binding: FragmentAccountPrivacyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userPreferences = UserPreferences(requireContext())
        binding.switchPrivacidad.isChecked = userPreferences._privacidad


        binding.switchPrivacidad.setOnCheckedChangeListener { _, isChecked ->
            //obtenemos el cambbio
            val priv = !userPreferences._privacidad
            val idUsuario = userPreferences.userId
            //Llamamos al método api

            Api.retrofitService.modificarPrivacidad(idUsuario, priv).enqueue(object : Callback<UsuarioResponse>{
                override fun onResponse(
                    call: Call<UsuarioResponse>,
                    response: Response<UsuarioResponse>
                ) {
                    if (response.isSuccessful){
                        //Efectuamos el cambio en las userPreferences, para que se mantenga
                        userPreferences._privacidad = priv
                    }else{
                       Toast.makeText(requireContext(), "Hubo un error inesperado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()
                }

            })
        }


        binding.menuAtras.setOnClickListener {
            view.findNavController().navigate(R.id.action_accountPrivacyFragment_to_settingsAppFragment)
        }
    }

}