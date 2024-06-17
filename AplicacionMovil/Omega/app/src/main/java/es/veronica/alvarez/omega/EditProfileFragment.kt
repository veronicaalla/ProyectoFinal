package es.veronica.alvarez.omega

import android.os.Bundle
import android.os.Handler
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


    /**
     * Método del ciclo de vida del Fragment
     * que recoge el valor que se les pasó por parametro
     */
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

    /**
     * Método donde se actualiza los datos de perfil del usuario
     */
    private fun actualizamosDatos() {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {

            Api.retrofitService.modificarPerfil(perfil.idUsuario, perfil).enqueue(object : Callback<Any>{

                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful){
                        val message = response.body()?.toString() ?: "Perfil actualizado correctamente"
                        Toast.makeText(requireContext(), "Perfil modificado", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Log.i("Error en el servidor", t.message.toString())
                //Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
                }

            })

            // Mostrar el ProgressBar
            binding.progressBar.visibility = View.VISIBLE

            // Ocultar el ProgressBar después de 5 segundos
            Handler().postDelayed({
                binding.progressBar.visibility = View.GONE
            }, 9000) //
            //Volvemos a el perfil
            requireView().findNavController().navigate(R.id.action_editProfileFragment_to_profileUserFragment)
        }

    }


    /**
     * Método que guarda los datos del perfil
     */
    private fun guardamosDatosEnPerfil() {
        perfil.nombre = binding.txtNombreUsuario.text.toString()
        perfil.informacion = binding.txtDescripcion.text.toString()
    }


    /**
     * Método que asigna a los elementos los propios valores del perfil
     */
    private fun asignamosDatos() {
        binding.txtNombreUsuario.setText(perfil.nombre)

        binding.txtUsername.setText(UserPreferences(requireContext())._username)
        binding.txtUsername.isEnabled = false

        binding.txtDescripcion.setText(perfil.informacion)
    }

}