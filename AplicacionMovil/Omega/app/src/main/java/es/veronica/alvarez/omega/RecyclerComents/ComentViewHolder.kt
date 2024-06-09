package es.veronica.alvarez.omega.RecyclerComents

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.ItemComentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * ViewHolder para un comentario en un RecyclerView.
 *
 * @param view La vista raíz del elemento de comentario.
 */
class ComentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemComentBinding.bind(view)


    /**
     * Vincula los datos de un comentario a la vista.
     *
     * @param comentarioResponse El comentario que se va a mostrar.
     * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona un comentario.
     */
    fun bind(
        comentarioResponse: ComentarioResponse,
        onItemSelected: (ComentarioResponse) -> Unit
    ) {


        Api.retrofitService.obtenerUsuarioPorId(comentarioResponse.idUsuario)
            .enqueue(object : Callback<UsuarioResponse> {
                override fun onResponse(
                    call: Call<UsuarioResponse>, response: Response<UsuarioResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.i("Succes", response.body().toString())
                        Log.i("Nombre del usuario", response.body()?.alias.toString())
                        binding.txtUsuario.text = response.body()?.alias.toString()

                    } else {
                        Log.i("no es succes", "error")
                    }
                }

                override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                    Log.i("OnFailure", t.message.toString())
                }

            })


        binding.txtFechaComentario.text = comentarioResponse.fecha
        binding.txtComentario.text = comentarioResponse.comentario

        //Si el usuario clica sobre reportar
        binding.txtReportar.setOnClickListener {
            val idUsuario = UserPreferences(it.context).userId
            Api.retrofitService.reportarComentario(comentarioResponse.id, idUsuario)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(it.context, "Comentario reportado correctamente", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.i("onFailure", t.message.toString())
                    }

                })
        }

        binding.root.setOnClickListener{ onItemSelected(comentarioResponse) }
    }

}

