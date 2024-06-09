package es.veronica.alvarez.omega.CreateUser

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.RecyclerBook.BookAdapter
import es.veronica.alvarez.omega.RecyclerFavorito.FavoritoAdapter
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentLiterarySelectionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LiterarySelectionFragment : Fragment() {

    private lateinit var binding: FragmentLiterarySelectionBinding
    private lateinit var listaGeneros: List<GeneroResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLiterarySelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtenerGeneros()

        binding.btnGuardar.setOnClickListener {
            val userPreferences = UserPreferences(requireContext())
            val lista = userPreferences.generosFavoritos.toList()
            val idUsuario = userPreferences.userId

            /* Construct the payload explicitly
            val payload = mutableMapOf<String, Any>()
            payload["idUsuario"] = idUsuario
            payload["idGeneros"] = lista*/

            Api.retrofitService.asociarGenerosAUsuario(idUsuario, lista).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Géneros asociados correctamente!", Toast.LENGTH_SHORT).show()
                        // Navegar a la siguiente pantalla si es necesario
                        view?.findNavController()?.navigate(R.id.action_literarySelectionFragment_to_startAppFragment)
                    } else {
                        Toast.makeText(requireContext(), "Hubo un fallo en la solicutud", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun obtenerGeneros() {
        Api.retrofitService.obtenerGeneros().enqueue(object : Callback<List<GeneroResponse>>{
            override fun onResponse(
                call: Call<List<GeneroResponse>>,
                response: Response<List<GeneroResponse>>
            ) {
                var generos = response.body()
                Log.i("generos", generos.toString())

                if (generos!!.isNotEmpty()) {
                    listaGeneros = generos
                    adjudicamosFuncionalidad()
                }
            }

            override fun onFailure(call: Call<List<GeneroResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun adjudicamosFuncionalidad() {
        //RecyclerView de libros aleatorios
        binding.rvGenerosLiterarios.layoutManager = LinearLayoutManager(context)
        binding.rvGenerosLiterarios.adapter = FavoritoAdapter(listaGeneros) {
            onItemSelected(it)
        }
        //endregion
    }

    private fun onItemSelected(it: GeneroResponse) {
        Log.i("Genero", it.toString())
    }
}

