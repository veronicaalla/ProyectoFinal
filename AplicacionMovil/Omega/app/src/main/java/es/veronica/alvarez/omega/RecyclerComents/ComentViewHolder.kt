package es.veronica.alvarez.omega.RecyclerComents

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.Model.UsuarioResponse
import es.veronica.alvarez.omega.databinding.ItemComentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemComentBinding.bind(view)

    fun bind(
        comentarioResponse: ComentarioResponse,
        onItemSelected: (ComentarioResponse) -> Unit
    ) {


        Api.retrofitService.obtenerUsuarioPorId(comentarioResponse.idUsuario).enqueue(object : Callback<UsuarioResponse>{
            override fun onResponse(
                call: Call<UsuarioResponse>,response: Response<UsuarioResponse>
            ) {
                if (response.isSuccessful){
                    Log.i("Succes", response.body().toString())
                    Log.i("Nombre del usuario", response.body()?.alias.toString())
                    binding.txtUsuario.text  = response.body()?.alias.toString()

                }else{
                    Log.i("no es succes", "error")
                }
            }

            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                Log.i("OnFailure", t.message.toString() )
            }

        })


        binding.txtFechaComentario.text = comentarioResponse.fecha
        binding.txtComentario.text = comentarioResponse.comentario

        binding.root.setOnClickListener { onItemSelected(comentarioResponse) }
    }




}

