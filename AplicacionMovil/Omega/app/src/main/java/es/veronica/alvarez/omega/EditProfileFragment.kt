package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.databinding.FragmentEditProfileBinding
import es.veronica.alvarez.omega.databinding.FragmentProfileUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var perfil: PerfilUsuarioResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Debemos recoger el valor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            perfil = it.getSerializable("perfil") as PerfilUsuarioResponse
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        asignamosDatos()

        binding.menuAtras.setOnClickListener {
            view.findNavController().navigate(R.id.action_editProfileFragment_to_profileUserFragment)
        }

        binding.btnGuardar.setOnClickListener {
            guardamosDatosEnPerfil()
            Log.i("Nuevos datos perfil", perfil.toString())
            actualizamosDatos()
        }
    }

    private fun actualizamosDatos() {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {

            Api.retrofitService.modificarPerfil(perfil.idUsuario, perfil).enqueue(object : Callback<Any>{

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful){
                        val message = response.body()?.toString() ?: "Perfil actualizado correctamente"
                        Toast.makeText(requireContext(), "Perfil modificado", Toast.LENGTH_LONG).show()
                        //Log.i("Respuesta", message)


                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}", t)
                }

            })
            //Volvemos a el perfil
            requireView().findNavController().navigate(R.id.action_editProfileFragment_to_profileUserFragment)
        }

    }

    private fun guardamosDatosEnPerfil() {
        perfil.nombre = binding.txtNombreUsuario.text.toString()
        perfil.informacion = binding.txtDescripcion.text.toString()
    }

    private fun asignamosDatos() {
        binding.txtNombreUsuario.setText(perfil.nombre)

        binding.txtUsername.setText(UserPreferences(requireContext())._username)
        binding.txtUsername.isEnabled = false

        binding.txtDescripcion.setText(perfil.informacion)
    }


}