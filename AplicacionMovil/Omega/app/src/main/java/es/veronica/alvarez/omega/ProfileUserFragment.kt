package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.RecyclerLibrary.LibraryAdapter
import es.veronica.alvarez.omega.databinding.FragmentProfileUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileUserFragment : Fragment() {

    private lateinit var binding: FragmentProfileUserBinding
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var perfil : PerfilUsuarioResponse

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var userPreferences = UserPreferences(requireContext())

        obtenerPerfil(userPreferences.userId)

        //region funcionalidad del toolBar
        binding.settings.setOnClickListener {
            view.findNavController().navigate(R.id.action_profileUserFragment_to_settingsAppFragment)
        }
        //endregion


        binding.btnEditarPerfil.setOnClickListener {
            val action = ProfileUserFragmentDirections
                .actionProfileUserFragmentToEditProfileFragment(perfil)
            findNavController().navigate(action)
        }


        //region Menu de navegacion
        bottomNavigationView = binding.bottomNavigationView

        // Establecer el listener
        bottomNavigationView!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.opcBuscar -> view.findNavController().navigate(R.id.action_profileUserFragment_to_searchFragment)

                R.id.opcCasa -> view.findNavController().navigate(R.id.action_profileUserFragment_to_startAppFragment)
            }
            false
        })
        //endregion
    }


    /**
     * Método que obtiene las bibliotecas que el usuario tiene asignadas
     */
    private fun obtenerBibliotecas() {
        Api.retrofitService.obtenerBibliotecas(perfil.idUsuario).enqueue(object : Callback<List<BibliotecaResponse>>{

            override fun onResponse(
                call: Call<List<BibliotecaResponse>>, response: Response<List<BibliotecaResponse>>
            ) {
               if (response.isSuccessful){
                    mostrarBibliotecas(response.body())
               }else{
                   Toast.makeText(requireContext(), "Hubo un error en la sollicitud", Toast.LENGTH_LONG).show()
               }
            }

            override fun onFailure(call: Call<List<BibliotecaResponse>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }


    /**
     * Método que asigna las bibliotecas al Recycler y su adaptador correspondiente
     * junto a su evento click
     */
    private fun mostrarBibliotecas(listaBibliotecas: List<BibliotecaResponse>?) {
        //RecyclerView de libros aleatorios
        binding.rvBibliotecas.layoutManager = LinearLayoutManager(context)
        binding.rvBibliotecas.adapter = listaBibliotecas?.let {
            LibraryAdapter(it) {
                onItemSelected(it)
            }
        }
        //endregion
    }


    /**
     * Método que asigna la funcionalidad al dar click sobre un item del Recycler
     */
    private fun onItemSelected(it: BibliotecaResponse) {
        val action = ProfileUserFragmentDirections
            .actionProfileUserFragmentToLibraryBooksFragment(it)
        findNavController().navigate(action)
    }


    /**
     * Método que obtiene el perfil del usuario determinado
     */
    private fun obtenerPerfil(userId: Int) {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Api.retrofitService.obtenerPerfilPorIdUsuario(userId)
                .enqueue(object : Callback<PerfilUsuarioResponse> {

                    override fun onResponse(
                        call: Call<PerfilUsuarioResponse>, response: Response<PerfilUsuarioResponse>
                    ) {
                        if (response.isSuccessful){
                            //Obtenemos el perfil
                            perfil = response.body()!!
                            asignamosPerfil(perfil)
                        }else{
                            Toast.makeText(requireContext(), "Hubo un error en la sollicitud", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<PerfilUsuarioResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }


    /**
     * Método donde se asigna los datos del perfil
     */
    private fun asignamosPerfil(perfil: PerfilUsuarioResponse?) {

        var userPreferences = UserPreferences(requireContext())
        binding.txtUser.text = userPreferences._username

        //Dependiendo de su privacidad, se usa una imagen u otra
        if (userPreferences._privacidad){
            //Publico = true
            binding.imgPrivacidad.setImageResource(R.drawable.baseline_lock_open_24_black)
        }else{
            //Privado = false
            binding.imgPrivacidad.setImageResource(R.drawable.baseline_lock_outline_black)
        }

        if (perfil != null) {
            binding.txtNombre.text = perfil.nombre
            binding.txtDescripcion.text = perfil.informacion
        }

        obtenerBibliotecas()
    }

}