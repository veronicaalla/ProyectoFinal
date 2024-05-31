package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            //view.findNavController().navigate(R.id.action_profileUserFragment_to_editProfileFragment)

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

    private fun obtenerBibliotecas() {
        Api.retrofitService.obtenerBibliotecas(perfil.idUsuario).enqueue(object : Callback<List<BibliotecaResponse>>{

            override fun onResponse(
                call: Call<List<BibliotecaResponse>>, response: Response<List<BibliotecaResponse>>
            ) {
               if (response.isSuccessful){
                    Log.i("Lista biblioteca", response.body().toString())
                    mostrarBibliotecas(response.body())
               }else{
                   Log.i("Error", "No satisfactorio ${response.message()}")
               }
            }

            override fun onFailure(call: Call<List<BibliotecaResponse>>, t: Throwable) {
                Log.i("Error", "Error de conexion")
            }

        })
    }

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

    private fun onItemSelected(it: BibliotecaResponse) {
        val action = ProfileUserFragmentDirections
            .actionProfileUserFragmentToLibraryBooksFragment(it)
        findNavController().navigate(action)
    }

    private fun obtenerPerfil(userId: Int) {
        Log.i("Id del usuario", userId.toString())
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Api.retrofitService.obtenerPerfilPorIdUsuario(userId)
                .enqueue(object : Callback<PerfilUsuarioResponse> {

                    override fun onResponse(
                        call: Call<PerfilUsuarioResponse>, response: Response<PerfilUsuarioResponse>
                    ) {
                        if (response.isSuccessful){
                            //Obtenemos el perfil
                            Log.i("Perfil usuario", response.body().toString())

                            perfil = response.body()!!
                            asignamosPerfil(perfil)
                        }else{
                            Log.i("Error", "No satisfactorio ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<PerfilUsuarioResponse>, t: Throwable) {
                        Log.i("Error", "Error de conexion")
                    }

                })
        }
    }

    private fun asignamosPerfil(perfil: PerfilUsuarioResponse?) {

        var userPreferences = UserPreferences(requireContext())
        binding.txtUser.text = userPreferences._username
        //binding.imgPrivacidad.setImageDrawable(R.drawable.) -> dependiendo de si es privado o no, se una uno u otro
        if (perfil != null) {
            binding.txtNombre.text = perfil.nombre
            binding.txtDescripcion.text = perfil.informacion
        }

        obtenerBibliotecas()
    }

}