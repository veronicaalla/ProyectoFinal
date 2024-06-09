package es.veronica.alvarez.omega.Books


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.RecyclerComents.ComentAdapter
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentSeeBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Fragmento para ver detalles de un libro.
 */
class SeeBookFragment : Fragment() {

    private lateinit var binding: FragmentSeeBookBinding
    private lateinit var libro: LibroResponse
    private lateinit var comentarios : List<ComentarioResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSeeBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Debemos recoger el valor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            libro = it.getSerializable("libro") as LibroResponse
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTitulo.text = libro.titulo
        binding.txtAutorLibro.text = libro.autor
        binding.txtLibroIsbn.text = libro.isbn
        binding.txtDescripcionLibro.text = libro.descripcion
        binding.txtPaginasLibro.text = "${libro.paginas} páginas"
        binding.txtFechaPublicacion.text =  if ((libro.fechaPublicacion != null)) libro.fechaPublicacion else ""

        obtenerGenero()
        obtenerComentarios()


        //region acciones del toolbar

        binding.imgBiblioteca.setOnClickListener {
            Toast.makeText(requireContext(), "Bibliotecas", Toast.LENGTH_LONG).show()
        }

        binding.imgLibroErroneo.setOnClickListener {
            val idUsuario = UserPreferences(requireContext()).userId
            Api.retrofitService.crearLibroErroneo(libro.id!!, idUsuario).enqueue(object : Callback<Void>{

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), "El libro ha sido reportado correctamente", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(requireContext(), "No se pudo reportar el libro", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
                }

            })
        }

        //endregion


        binding.imgEnviarComentario.setOnClickListener {
            //Comprobamos que el campo no este vacio
            if (binding.editTextComentario.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "No se ha insertado ningun comentario", Toast.LENGTH_LONG).show()
            }else{
                //Si no esta vacio, lo almacenamos
                val comentario = binding.editTextComentario.text.toString()
                crearComentario (comentario);
            }
        }

        binding.btnValoracionUsuario.setOnClickListener {
            val action = SeeBookFragmentDirections.actionSeeBookFragmentToUserRatingFragment(libro)
            findNavController().navigate(action)
        }

    }


    /**
     * Crea un comentario sobre el libro.
     * @param comentario El comentario a crear.
     */
    private fun crearComentario(comentario: String) {
        val idLibro = libro.id
        val userPreferences = UserPreferences(requireContext())
        val idUsario = userPreferences.userId
        Api.retrofitService.crearComentario(idLibro!!, idUsario, comentario).enqueue(object : Callback<ComentarioResponse> {
            override fun onResponse(
                call: Call<ComentarioResponse>,
                response: Response<ComentarioResponse>
            ) {
                if (response.isSuccessful){

                }else{
                    Log.i("No succesful", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ComentarioResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }


    /**
     * Método que obtiene el género del libro
     */
    private fun obtenerGenero (){
        Api.retrofitService.obtenerGeneroPorId(libro.genero!!).enqueue(object : Callback<GeneroResponse>{

            override fun onResponse(
                call: Call<GeneroResponse>,response: Response<GeneroResponse>
            ) {
                if (response.isSuccessful){
                    var genero =  response.body()!!.nombre
                    binding.txtGeneroLibro.text = genero
                }
            }

            override fun onFailure(call: Call<GeneroResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }


    /**
     *Método que obtiene los comentarios del libro
     */
    private fun obtenerComentarios() {

        Api.retrofitService.obtenerComentariosPorLibro(libro.id!!).enqueue(object : Callback <List<ComentarioResponse>>{
            override fun onResponse(
                call: Call<List<ComentarioResponse>>,
                response: Response<List<ComentarioResponse>>
            ) {
                if (response.isSuccessful){
                    comentarios = response.body()!!
                    adjudicamosFuncionalidad()
                }else{
                    Toast.makeText(requireContext(), "Hubo un error inesperado", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ComentarioResponse>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }


    /**
     * Método que asigna los comentarios al Recycler con su Adapter indicado
     */
    private fun adjudicamosFuncionalidad() {

        binding.rvComentarios.layoutManager = LinearLayoutManager(context)
        binding.rvComentarios.adapter = ComentAdapter(comentarios) {
            onItemSelected(it)
        }

    }


    /**
     * Método cuya funcionalidad se asignará a futuro
     */
    private fun onItemSelected(it: ComentarioResponse) {
        Toast.makeText(requireContext(), "Se ha seleccionado", Toast.LENGTH_LONG).show()
    }


}