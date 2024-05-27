package es.veronica.alvarez.omega.RecyclerBook

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.DataApi.Api
import es.veronica.alvarez.omega.Model.BookResponse
import es.veronica.alvarez.omega.Model.ValoracionLibroResponse
import es.veronica.alvarez.omega.databinding.ItemBookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemBookBinding.bind(view)

    fun bind(
        bookResponse : BookResponse,
        onItemSelected: (BookResponse) -> Unit
    ){
        binding.txtTitulo.text = bookResponse.titulo
        binding.txtSinopsis.text = obtenerDescripcionCorta(bookResponse.descripcion)
        binding.txtAutor.text = "Autor: ${bookResponse.autor}"
        obtenerValoracionLibro(bookResponse.id)

        binding.root.setOnClickListener { onItemSelected(bookResponse) }
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