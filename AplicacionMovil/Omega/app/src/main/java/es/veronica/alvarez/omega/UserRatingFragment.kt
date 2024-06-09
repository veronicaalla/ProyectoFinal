package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.Model.ValoracionUsuarioResponse
import es.veronica.alvarez.omega.databinding.FragmentSeeBookBinding
import es.veronica.alvarez.omega.databinding.FragmentUserRatingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRatingFragment : Fragment() {

    private lateinit var binding: FragmentUserRatingBinding
    private lateinit var libro: LibroResponse
    private var valoracionUsuario: ValoracionUsuarioResponse = ValoracionUsuarioResponse()
    private var valorado: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Método del ciclo de vida del Fragment
     * que recoge el valor que se les pasó por parametro
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            libro = it.getSerializable("libro") as LibroResponse
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTitulo.text = libro.titulo
        obtenerValoracionUsuario()

        binding.btnGuardar.setOnClickListener {
            //Comprobamos si la valoración existia o no
            asignamosValores()

            if (valorado) {
                actualizamosValoracion()
            } else {
                creamosValoracion()
            }
        }
    }

    /**
     * Método que asigna a la 'Valoracion del usuario' los datos asignados
     */
    private fun asignamosValores() {
        valoracionUsuario.playList = binding.editPlayList.text.toString()
        valoracionUsuario.personajeFav = binding.editPersonajeFav.text.toString()
        valoracionUsuario.personajeOdiado = binding.editPersonajeOdiado.text.toString()
        valoracionUsuario.recomendacion = binding.switchRecomendacion.isChecked
        valoracionUsuario.fraseIconica = binding.editFraseIconica.text.toString()
        valoracionUsuario.opinion = binding.editOpinion.text.toString()
        valoracionUsuario.puntuacion = binding.ratinValoracion.rating.toDouble()
    }


    /**
     * Método que crea la valoracion, cuando el usuario no tiene ninguna asociada
     */
    private fun creamosValoracion() {
        val idLibro = libro.id
        val idUsuario = UserPreferences(requireContext()).userId
        Api.retrofitService.crearValoracionUsuario(idUsuario, idLibro!!, valoracionUsuario)
            .enqueue(object : Callback<ValoracionUsuarioResponse> {
                override fun onResponse(
                    call: Call<ValoracionUsuarioResponse>,
                    response: Response<ValoracionUsuarioResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "Valoración creada exitosamente", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "Error al crear la valoración", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ValoracionUsuarioResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
                }

            })
    }


    /**
     * Método en el que se actualiza la valoración individual de un libro
     */
    private fun actualizamosValoracion() {
        val idLibro = libro.id
        val idUsuario = UserPreferences(requireContext()).userId

        Api.retrofitService.actualizarValoracion(idUsuario, idLibro!!, valoracionUsuario).enqueue(object : Callback<Any>{

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful){
                    Toast.makeText(requireContext(), "Valoración actualizada", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "Error al actualizar la valoración", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
            }

        })
    }


    /**
     * Método mediante el cual obtenemos la valoración personal del usuario
     * de un determinado libro.
     */
    private fun obtenerValoracionUsuario() {
        val idLibro = libro.id
        val idUsuario = UserPreferences(requireContext()).userId
        Api.retrofitService.obtenerValoracionLibroPorUsuario(idLibro!!, idUsuario)
            .enqueue(object : Callback<ValoracionUsuarioResponse> {

                override fun onResponse(
                    call: Call<ValoracionUsuarioResponse>,
                    response: Response<ValoracionUsuarioResponse>
                ) {
                    if (response.isSuccessful) {
                        valorado = true
                        valoracionUsuario = response.body()!!
                        asignamosDatos()
                    } else {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ValoracionUsuarioResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error en el servidor", Toast.LENGTH_LONG).show()
                }
            })
    }


    /**
     * Método mediante el cual asignamos a los EditText
     * la 'Valoración del Usuario'
     */
    private fun asignamosDatos() {
        binding.editPersonajeFav.setText(valoracionUsuario.personajeFav)
        binding.editPersonajeOdiado.setText(valoracionUsuario.personajeOdiado)
        binding.editFraseIconica.setText(valoracionUsuario.fraseIconica)
        binding.editPlayList.setText(valoracionUsuario.playList)
        binding.ratinValoracion.rating = valoracionUsuario.puntuacion.toFloat()
        binding.editOpinion.setText(valoracionUsuario.opinion)
        binding.switchRecomendacion.isChecked = valoracionUsuario.recomendacion
    }

}