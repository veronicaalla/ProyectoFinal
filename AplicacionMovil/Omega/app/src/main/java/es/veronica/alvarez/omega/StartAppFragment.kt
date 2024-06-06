package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.GeneroUsuarioResponse
import es.veronica.alvarez.omega.RecyclerBook.BookAdapter
import es.veronica.alvarez.omega.databinding.FragmentStartAppBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var listaLibros: MutableList<LibroResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        obtenerGenerosUsuario()

        //region funcionalidad a futuro
        binding.menuChat.setOnClickListener {

        }

        binding.menuNotificaciones.setOnClickListener {

        }
        //endregion

        //region Funcionalidad de añadir
        var visible = false;
        binding.addElement.setOnClickListener {
            if (visible){
                //Ocultamos los elementos
                binding.btnCrearLibro.visibility = View.GONE
                binding.btnCrearBiblioteca.visibility = View.GONE
                visible = false
            }else{
                binding.btnCrearLibro.visibility = View.VISIBLE
                binding.btnCrearBiblioteca.visibility = View.VISIBLE
                visible = true
            }
        }

        binding.btnCrearLibro.setOnClickListener {
            //Ocultamos los elementos
            binding.btnCrearLibro.visibility = View.GONE
            binding.btnCrearBiblioteca.visibility = View.GONE
            visible = false

            //Redireccionamos la navegación
            view.findNavController().navigate(R.id.action_startAppFragment_to_createBookFragment)
        }


        binding.btnCrearBiblioteca.setOnClickListener {
            //Ocultamos los elementos
            binding.btnCrearLibro.visibility = View.GONE
            binding.btnCrearBiblioteca.visibility = View.GONE
            visible = false

            mostrarDialogo()
        }
        //endregion

        //region Menu de navegacion
        bottomNavigationView = binding.bottomNavigationView

        // Establecer el listener
        bottomNavigationView!!.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.opcBuscar -> view.findNavController().navigate(R.id.action_startAppFragment_to_searchFragment)

                R.id.opcPerfil -> view.findNavController().navigate(R.id.action_startAppFragment_to_profileUserFragment)
            }
            false
        })
        //endregion

    }

    private fun mostrarDialogo() {
        val inputEditTextField = EditText(requireContext())
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Nueva biblioteca")
            .setMessage("Introduce el nombre de la nueva biblioteca")
            .setView(inputEditTextField)
            .setPositiveButton("Añadir"){ _,_ ->
                val nombreBiblioteca = inputEditTextField .text.toString()
                crearBiblioteca (nombreBiblioteca)
            }
            .setNegativeButton("Cancelar", null)
            .create()
        dialog.show()

    }

    private fun crearBiblioteca(nombreBiblioteca: String) {
        var idUsuario = UserPreferences(requireContext()).userId

        var bibliotecaResponse = BibliotecaResponse()
        bibliotecaResponse.nombre = nombreBiblioteca

        Api.retrofitService.crearBiblioteca(idUsuario, bibliotecaResponse).enqueue(object : Callback<BibliotecaResponse>{
            override fun onResponse(
                call: Call<BibliotecaResponse>, response: Response<BibliotecaResponse>
            ) {
                if (response.isSuccessful){
                    Log.i("Crear biblioteca", response.body().toString())
                    Toast.makeText(requireContext(), "Biblioteca creada correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Log.i("Crear noSucces", response.message().toString())
                }
            }

            override fun onFailure(call: Call<BibliotecaResponse>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }

        })
    }

    private fun obtenerGenerosUsuario() {
        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            var userPreferences = UserPreferences(requireContext())
            Api.retrofitService.obtenerGenerosPorUsuario(userPreferences.userId)
                .enqueue(object : Callback<List<GeneroUsuarioResponse>> {
                    override fun onResponse(
                        call: Call<List<GeneroUsuarioResponse>>,
                        response: Response<List<GeneroUsuarioResponse>>
                    ) {
                        if (response.isSuccessful) {
                            //Convertimos la lista a generos
                            var listaResponse: List<GeneroUsuarioResponse>? = response.body()
                            //Log.i("Lista", listaResponse.toString())

                            //Obtenemos solo los id
                            val idsGenero: List<Int> = listaResponse!!.map { it.idGenero }
                            Log.i("Generos", idsGenero.toString())

                            for (idGenero in idsGenero) {
                                // Haz algo con idGenero
                                obtenerLibrosGenero(idGenero)
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<GeneroUsuarioResponse>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }

    }

    private fun obtenerLibrosGenero(idGenero: Int) {

        context?.let { Api.initialize(it.applicationContext) }
        context?.applicationContext?.let {
            Log.i("Lista id", idGenero.toString())
            Api.retrofitService.obtenerLibrosPorGenero(idGenero)
                .enqueue(object : Callback<List<LibroResponse>> {

                    override fun onResponse(
                        call: Call<List<LibroResponse>>, response: Response<List<LibroResponse>>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            // Si la lista aún no está inicializada, inicializarla
                            if (!::listaLibros.isInitialized) {
                                listaLibros = mutableListOf()
                            }
                            // Agregar los libros a la lista existente
                            //listaLibros.addAll(response.body()!!)
                            // Recorrer la lista de libros y realizar alguna acción con cada libro
                            for (libro in response.body()!!) {
                                listaLibros.add(libro)
                            }
                            adjudicamosFuncionalidad()
                        } else {
                            // Maneja el caso de respuesta no exitosa
                        }
                    }

                    override fun onFailure(call: Call<List<LibroResponse>>, t: Throwable) {
                        // Maneja el fallo en la llamada
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
    }

    private fun onItemSelected(it: LibroResponse) {
        val action = StartAppFragmentDirections
            .actionStartAppFragmentToSeeBookFragment(it)
        findNavController().navigate(action)
    }


}