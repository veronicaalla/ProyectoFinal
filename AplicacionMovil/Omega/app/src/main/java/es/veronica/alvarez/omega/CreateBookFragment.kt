package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.databinding.FragmentCreateBookBinding
import es.veronica.alvarez.omega.databinding.FragmentSeeBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateBookFragment : Fragment() {

    private lateinit var binding: FragmentCreateBookBinding
    private var libro = LibroResponse()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cargarSpinner()
        //La unica funcion con la que cuenta el usuario, es la de crear la biblioteca
        binding.btnCrear.setOnClickListener {
            if (validarDatos()){
                asignamosDatos()
                crearLibro()
            }

        }

        binding.btnCancelar.setOnClickListener {
            view.findNavController().navigate(R.id.action_createBookFragment_to_startAppFragment)
        }


        binding.spinnerGenero.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedGenero = parent.getItemAtPosition(position) as GeneroResponse
                val idGenero = selectedGenero.id

                //Le asignamos a el libro el generoId
                libro.genero = idGenero
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }
    }


    /**
     * Método que valida que todos los campos sean correctos
     */
    private fun validarDatos(): Boolean {

        if (binding.ediTextTitulo.text.isEmpty() || binding.ediTextTitulo.text.contentEquals("")){
            MensajeValidaciones("Debe introducir un titulo")
            return false
        }

        if (binding.ediTextAutor.text.isEmpty() || binding.ediTextAutor.text.contentEquals("")){
            MensajeValidaciones("Debe indicar el autor")
            return false
        }

        if (binding.ediTextIsbn.text.isEmpty() || binding.ediTextIsbn.text.contentEquals("")){
            MensajeValidaciones("Debe indicar su ISBN")
            return false
        }

        if (binding.editTextDescripcion.text.isEmpty() || binding.editTextDescripcion.text.contentEquals("")){
            MensajeValidaciones("Debe introducir la sinopsis del libro")
            return false
        }


        if (binding.ediTextPaginas.text.isEmpty() || binding.ediTextPaginas.text.contentEquals("")){
            MensajeValidaciones("Debe indicar cuantas páginas tiene el libro")
            return false
        }


        return true
    }


    /**
     * Método que muestra un mensaje de error en caso de que algun
     * atributo este vacio o mal formado
     */
    private fun MensajeValidaciones(mensaje: String) {
        // Creamos la ventana emergente
        val dialog = context?.let {
            MaterialAlertDialogBuilder(it).setTitle("ERROR").setMessage(mensaje)
                .setNegativeButton("OK", null).create()

        }
        if (dialog != null) {
            dialog.show()
        }
    }


    /**
     * Método que crea un libro llamando su método API
     */
    private fun crearLibro() {
        //obtenemos el usuario que esta registrado
        //y por consiguiente esta realizando la creacion del libro
        val idUsuario = UserPreferences(requireContext()).userId

        Api.retrofitService.crearLibro(idUsuario, libro).enqueue(object : Callback<LibroResponse>{

            override fun onResponse(call: Call<LibroResponse>, response: Response<LibroResponse>) {

                if (response.isSuccessful){
                    Toast.makeText(requireContext(), "Libro creado correctamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "Error al crear el libro", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LibroResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en la conexión del servidor", Toast.LENGTH_LONG).show()
            }

        })
    }

    /**
     * Método que carga en el spinner los géneros que hay almacenados en la base de datos
     */
    private fun cargarSpinner() {
        Api.retrofitService.obtenerGeneros().enqueue(object : Callback<List<GeneroResponse>> {
            override fun onResponse(call: Call<List<GeneroResponse>>, response: Response<List<GeneroResponse>>) {
                if (response.isSuccessful) {
                    val generos = response.body()
                    Log.i("Succesful", generos.toString())
                    if (generos != null) {
                        val adapter = GeneroAdapter(requireContext(), generos)
                        binding.spinnerGenero.adapter = adapter
                    }
                } else {
                    Log.i("noSucces", response.message().toString())
                }
            }

            override fun onFailure(call: Call<List<GeneroResponse>>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }
        })
    }


    /**
     * Método que asigna los datos que ha introducido el usuario
     * en el propio libro
     */
    private fun asignamosDatos() {
        libro.titulo = binding.ediTextTitulo.text.toString()
        libro.autor = binding.ediTextAutor.text.toString()
        libro.isbn = binding.ediTextIsbn.text.toString()
        libro.descripcion = binding.editTextDescripcion.text.toString()
        libro.paginas = binding.ediTextPaginas.text.toString().toInt()
        libro.fechaPublicacion = binding.editTextDate.text.toString()
    }

}