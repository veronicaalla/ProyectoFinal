package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.BookResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.databinding.FragmentStartAppBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var listaLibros: List<BookResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtenerGenerosUsuario()

        binding.menuChat.setOnClickListener {

        }

        binding.menuNotificaciones.setOnClickListener {

        }

        //region Menu de navegacion
        bottomNavigationView = binding.bottomNavigationView

        // Establecer el listener
        bottomNavigationView!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.opcBuscar ->  view.findNavController()
                    .navigate(R.id.action_startAppFragment_to_searchFragment)
            }
            false
        })
        //endregion

    }

    private fun obtenerGenerosUsuario() {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            var userPreferences = UserPreferences(requireContext())
            Api.retrofitService.obtenerGenerosPorUsuario(userPreferences.userId).enqueue(object : Callback<List<GeneroUsuarioResponse>>{
                override fun onResponse( call: Call<List<GeneroUsuarioResponse>>, response: Response<List<GeneroUsuarioResponse>>
                ) {
                    if(response.isSuccessful){
                        //Convertimos la lista a generos
                        var listaResponse: List<GeneroUsuarioResponse>? = response.body()
                        //Log.i("Lista", listaResponse.toString())

                        //Obtenemos solo los id
                        val idsGenero: List<Int> = listaResponse!!.map { it.idGenero }
                        Log.i("Generos", idsGenero.toString())
                    }
                }

                override fun onFailure(call: Call<List<GeneroUsuarioResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
        /*
        * Call<List<GeneroUsuario>> call = apiInterface.obtenerGenerosPorUsuario(idUsuario);
        call.enqueue(new Callback<List<GeneroUsuario>>() {
            @Override
            public void onResponse(Call<List<GeneroUsuario>> call, Response<List<GeneroUsuario>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GeneroUsuario> generos = response.body();
                    List<Integer> idsGenero = new ArrayList<>();
                    for (GeneroUsuario genero : generos) {
                        idsGenero.add(genero.getIdGenero());
                    }
                    // Ahora tienes una lista de idsGenero que puedes usar como necesites
                    // Por ejemplo, actualizar una vista, log, etc.
                } else {
                    // Manejar el caso de respuesta no exitosa
                }
            }

            @Override
            public void onFailure(Call<List<GeneroUsuario>> call, Throwable t) {
                // Manejar el fallo en la llamada
            }
        });*/
    }


}