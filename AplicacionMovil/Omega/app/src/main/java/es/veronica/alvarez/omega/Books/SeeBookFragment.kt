package es.veronica.alvarez.omega.Books

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.RecyclerBook.BookAdapter
import es.veronica.alvarez.omega.RecyclerComents.ComentAdapter
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.FragmentSeeBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        Log.i("Id del genero" , libro.id.toString())
        obtenerGenero()
        obtenerComentarios()

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
    }

    private fun crearComentario(comentario: String) {
        /*@Field("idLibro") idLibro: Int,
        @Field("idUsuario") idUsuario: Int,
        @Field("comentario") comentario: String*/

        val idLibro = libro.id
        val userPreferences = UserPreferences(requireContext())
        val idUsario = userPreferences.userId
        Api.retrofitService.crearComentario(idLibro, idUsario, comentario).enqueue(object : Callback<ComentarioResponse> {
            override fun onResponse(
                call: Call<ComentarioResponse>,
                response: Response<ComentarioResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ComentarioResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun obtenerGenero (){
        Api.retrofitService.obtenerGeneroPorId(libro.genero).enqueue(object : Callback<GeneroResponse>{

            override fun onResponse(
                call: Call<GeneroResponse>,response: Response<GeneroResponse>
            ) {

                if (response.isSuccessful){
                    var genero =  response.body()!!.nombre
                    binding.txtGeneroLibro.text = genero
                }else{
                    Log.i("Genero", response.body().toString())
                    Log.i("Mensaje error ", response.message().toString())
                    Log.i("Codigo error ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<GeneroResponse>, t: Throwable) {
                Log.i("OnFailure ", t.message.toString())
            }

        })
    }

    private fun obtenerComentarios() {

        Api.retrofitService.obtenerComentariosPorLibro(1).enqueue(object : Callback <List<ComentarioResponse>>{
            override fun onResponse(
                call: Call<List<ComentarioResponse>>,
                response: Response<List<ComentarioResponse>>
            ) {
                if (response.isSuccessful){
                    comentarios = response.body()!!
                    adjudicamosFuncionalidad()
                }else{
                    Log.i("No succes", response.message())
                }
            }

            override fun onFailure(call: Call<List<ComentarioResponse>>, t: Throwable) {
                Log.i("OnFailure ", t.message.toString())
            }

        })
    }

    private fun adjudicamosFuncionalidad() {
        //RecyclerView de libros aleatorios
        binding.rvComentarios.layoutManager = LinearLayoutManager(context)
        binding.rvComentarios.adapter = ComentAdapter(comentarios) {
            onItemSelected(it)
        }
        //endregion
    }

    private fun onItemSelected(it: ComentarioResponse) {
        Toast.makeText(requireContext(), "Se ha seleccionado", Toast.LENGTH_LONG).show()
    }



}