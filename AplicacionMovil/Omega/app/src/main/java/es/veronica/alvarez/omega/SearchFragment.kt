package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.databinding.FragmentSearchBinding
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.RecyclerBook.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var listaLibros: List<LibroResponse>
    private lateinit var listaTotalLibros: List<LibroResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mostrarLibrosAleatorios()
        todosLosLibros()

        //region BUSQUEDAS USUARIO
        binding.txtSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText ?: ""  // Si el nuevo texto es nulo, asigna una cadena vacía

                // Ahora puedes hacer lo que necesites con el texto, como filtrar la lista de patrocinadores
                // o realizar cualquier otra acción basada en el texto de búsqueda
                Log.d("Texto de búsqueda", searchText)

                //Al realizar la busqueda, sacamos todos los libros

                // Filtrar la lista de patrocinadores para obtener aquellos que contienen el texto de búsqueda en el nombre
                val filteredList = listaTotalLibros.filter { libro ->
                    libro.titulo.contains(searchText, ignoreCase = true)
                }

                // Ahora puedes hacer lo que necesites con la lista filtrada, como actualizar el RecyclerView
                // o realizar cualquier otra acción basada en los patrocinadores que coinciden con el texto de búsqueda
                // Actualizar el RecyclerView con la lista filtrada
                (binding.rvBusqueda.adapter as BookAdapter).updateBooks(filteredList)
                return true
            }
        })

        //endregion

        //region Menu de navegacion
        bottomNavigationView = binding.bottomNavigationView

        // Establecer el listener
        bottomNavigationView!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.opcCasa -> view.findNavController().navigate(R.id.action_searchFragment_to_startAppFragment)

                R.id.opcPerfil -> view.findNavController().navigate(R.id.action_searchFragment_to_profileUserFragment)
            }
            false
        })
        //endregion
    }

    private fun todosLosLibros() {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Api.retrofitService.obtenerLibros().enqueue(object : Callback<List<LibroResponse>> {
                override fun onResponse(
                    call: Call<List<LibroResponse>>,
                    response: Response<List<LibroResponse>>
                ) {
                    var libros = response.body()
                    Log.i("Todos los libros", libros.toString())

                    if (libros!!.isNotEmpty()) {
                        listaTotalLibros = libros
                    }
                }

                override fun onFailure(call: Call<List<LibroResponse>>, t: Throwable) {
                    Log.i("Error login", t.toString())
                    Log.i("error", t.printStackTrace().toString())
                }
            })
        }
    }


    private fun mostrarLibrosAleatorios(){
        //listaTotalLibros
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Api.retrofitService.obtenerTotalLibros().enqueue(object : Callback <List<LibroResponse>>{
                override fun onResponse(
                    call: Call<List<LibroResponse>>,
                    response: Response<List<LibroResponse>>
                ) {
                    var libros = response.body()
                    Log.i("Libros aleatorios", libros.toString())

                    if (libros!!.isNotEmpty()) {
                        listaLibros = libros
                        adjudicamosFuncionalidad()
                    }
                }

                override fun onFailure(call: Call<List<LibroResponse>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }


    private fun adjudicamosFuncionalidad() {
        //RecyclerView de libros aleatorios
        binding.rvBusqueda.layoutManager = LinearLayoutManager(context)
        binding.rvBusqueda.adapter = BookAdapter(listaLibros) {
            onItemSelected(it)
        }
        //endregion
    }

    private fun onItemSelected(it: LibroResponse) {
        Toast.makeText(requireContext(), "Libro ${it.titulo}", Toast.LENGTH_LONG).show()
    }

}