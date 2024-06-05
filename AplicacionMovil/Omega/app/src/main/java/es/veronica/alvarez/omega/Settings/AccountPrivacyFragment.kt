package es.veronica.alvarez.omega.Settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentAccountPrivacyBinding
import es.veronica.alvarez.omega.databinding.FragmentSeeBookBinding
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

    //_privacidad
    /*
    * fun modificarPrivacidad(
        @Path("idUsuario") idUsuario: Int,
        @Query("privacidad") privacidad: Boolean
        * */

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
                        Log.i("Modificar privacidad", response.body().toString())
                        userPreferences._privacidad = priv
                    }else{
                        Log.i("is not succesful", response.message().toString())
                    }
                }

                override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                    Log.i("onFailure", t.message.toString())
                }

            })
        }
    }

}