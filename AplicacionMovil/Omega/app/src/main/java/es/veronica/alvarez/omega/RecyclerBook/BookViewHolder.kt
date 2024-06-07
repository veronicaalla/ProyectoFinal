package es.veronica.alvarez.omega.RecyclerBook

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.databinding.ItemBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemBookBinding.bind(view)

    fun bind(
        libroResponse : LibroResponse,
        onItemSelected: (LibroResponse) -> Unit
    ){
        binding.txtTitulo.text = libroResponse.titulo
        binding.txtSinopsis.text = obtenerDescripcionCorta(libroResponse.descripcion!!)
        binding.txtAutor.text = "Autor: ${libroResponse.autor}"
        obtenerValoracionLibro(libroResponse.id!!)

        binding.root.setOnClickListener { onItemSelected(libroResponse) }
    }

    fun obtenerDescripcionCorta(descripcion: String, maxPalabras: Int = 15): String {
        val palabras = descripcion.split(" ")
        return if (palabras.size > maxPalabras) {
            palabras.take(maxPalabras).joinToString(" ") + "..."
        } else {
            descripcion
        }
    }

    fun obtenerValoracionLibro(id: Int) {
        // Llamar al método obtenerValoracionLibro
        Api.retrofitService.obtenerValoracionLibro(id).enqueue(object : Callback<Double>{
            override fun onResponse(call: Call<Double>, response: Response<Double>) {
               if (response.isSuccessful){
                   val valoracion = response.body()
                   Log.i("Valoracion libro", valoracion.toString())
                   binding.valoracionMedia.rating = valoracion!!.toFloat()
               }
            }

            override fun onFailure(call: Call<Double>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}