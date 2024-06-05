package es.veronica.alvarez.omega

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var valoracionUsuario: ValoracionUsuarioResponse? = null
    private var valorado: Boolean = false
    var usuario: UsuarioResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRatingBinding.inflate(inflater, container, false)
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

        obtenerValoracionUsuario()

        binding.btnGuardar.setOnClickListener {
            //Comprobamos si la valoración existia o no
            obtenerUsuario()
            asignamosValores()
            if (valorado){
                actualizamosValoracion()
            }else{
                Log.i("Valoracion libro", "No creado")
                creamosValoracion()
            }
        }
    }

    private fun asignamosValores() {
        valoracionUsuario!!.usuario = usuario!!
        valoracionUsuario!!.libro = libro
        valoracionUsuario!!.playList = binding.editPlayList.text.toString()
        valoracionUsuario!!.personajeFav = binding.editPersonajeFav.text.toString()
        valoracionUsuario!!.personajeOdiado = binding.editPersonajeOdiado.text.toString()
        valoracionUsuario!!.recomendacion = binding.switchRecomendacion.isChecked
        valoracionUsuario!!.fraseIconica = binding.editFraseIconica.text.toString()
        valoracionUsuario!!.opinion = binding.editOpinion.text.toString()
        valoracionUsuario!!.puntuacion = binding.ratinValoracion.rating.toDouble()
    }

    private fun obtenerUsuario() {
        Api.retrofitService.obtenerUsuarioPorId(UserPreferences(requireContext()).userId).enqueue(object : Callback<UsuarioResponse>{
            override fun onResponse(
                call: Call<UsuarioResponse>,
                response: Response<UsuarioResponse>
            ) {
                if (response.isSuccessful){
                    Log.i("usuario", response.body().toString())
                    usuario = response.body()
                }else{
                    Log.i("no succedful", "No se ha encontrado")
                }
            }

            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }

        })
    }

    private fun creamosValoracion() {
        val idLibro = libro.id
        val idUsuario = UserPreferences(requireContext()).userId
        Api.retrofitService.crearValoracionUsuario(idUsuario, idLibro, valoracionUsuario!!).enqueue(object : Callback <ValoracionUsuarioResponse>{
            override fun onResponse(
                call: Call<ValoracionUsuarioResponse>,
                response: Response<ValoracionUsuarioResponse>
            ) {
                if (response.isSuccessful){
                    Log.i("Succesful", "Creado correctamente")
                    Log.i("Valoracion", response.body().toString())
                }else{
                    Log.i("No succesful", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ValoracionUsuarioResponse>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }

        })
    }

    private fun actualizamosValoracion() {
        TODO("Not yet implemented")
    }

    private fun obtenerValoracionUsuario() {
        val idLibro = libro.id
        val idUsuario = UserPreferences(requireContext()).userId
        Api.retrofitService.obtenerValoracionLibroPorUsuario(idLibro, idUsuario).enqueue(object : Callback<ValoracionUsuarioResponse>{

            override fun onResponse(
                call: Call<ValoracionUsuarioResponse>,
                response: Response<ValoracionUsuarioResponse>
            ) {
                if (response.isSuccessful){
                    valorado = true
                    Log.i("Succes", response.body().toString())
                    valoracionUsuario = response.body()!!
                    asignamosDatos()
                }else{
                    Log.i("Not succesful", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ValoracionUsuarioResponse>, t: Throwable) {
                Log.i("onFailure", t.message.toString())
            }

        })
    }

    private fun asignamosDatos() {
        binding.editPersonajeFav.setText(valoracionUsuario!!.personajeFav)
        binding.editPersonajeOdiado.setText(valoracionUsuario!!.personajeOdiado)
        binding.editFraseIconica.setText(valoracionUsuario!!.fraseIconica)
        binding.editPlayList.setText(valoracionUsuario!!.playList)
        binding.ratinValoracion.rating = valoracionUsuario!!.puntuacion.toFloat()
        binding.editOpinion.setText(valoracionUsuario!!.opinion)
        binding.switchRecomendacion.isChecked = valoracionUsuario!!.recomendacion
    }

}