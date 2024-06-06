package es.veronica.alvarez.omega.Librarys

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.LibrosBibliotecaResponse
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.RecyclerBook.BookAdapter
import es.veronica.alvarez.omega.databinding.FragmentLibraryBooksBinding
import es.veronica.alvarez.omega.databinding.FragmentProfileUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LibraryBooksFragment : Fragment() {

    private lateinit var binding: FragmentLibraryBooksBinding
    private lateinit var biblioteca: BibliotecaResponse
    private lateinit var listaLibros: List<LibroResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLibraryBooksBinding.inflate(inflater, container, false)
        return binding.root

    }

    //Debemos recoger el valor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            biblioteca = it.getSerializable("biblioteca") as BibliotecaResponse
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtNombreBiblioteca.text = biblioteca.nombre

        obtenerLibrosBiblioteca()
    }

    private fun obtenerLibrosBiblioteca() {
        //Log.i("nombre biblioteca", biblioteca.nombre)
        var bibliotecaId = biblioteca.id

        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Api.retrofitService.obtenerLibrosPorBiblioteca(bibliotecaId)
                .enqueue(object : Callback<List<LibrosBibliotecaResponse>> {

                    override fun onResponse(
                        call: Call<List<LibrosBibliotecaResponse>>,
                        response: Response<List<LibrosBibliotecaResponse>>
                    ) {
                        if (response.isSuccessful) {
                            Log.i("Lista libros succes", response.body().toString())
                            val listaLibrosBiblioteca = response.body()!!
                            listaLibros= listaLibrosBiblioteca.map { it.libro }
                            //Log.i("Lista de libros", listaLibros.toString())
                            adjudicamosFuncionalidad()

                        } else {
                            Log.i("No succes", response.message())
                        }
                    }

                    override fun onFailure(
                        call: Call<List<LibrosBibliotecaResponse>>,
                        t: Throwable
                    ) {
                        Log.i("Error", "Error de conexion")
                    }

                })
        }
    }

    private fun adjudicamosFuncionalidad() {
        //RecyclerView de libros aleatorios
        binding.rvLibros.layoutManager = LinearLayoutManager(context)
        binding.rvLibros.adapter = BookAdapter(listaLibros) {
            onItemSelected(it)
        }
        //endregion

    }

    private fun onItemSelected(it: LibroResponse) {
        Toast.makeText(requireContext(), "Libro ${it.titulo}", Toast.LENGTH_LONG).show()
    }


}