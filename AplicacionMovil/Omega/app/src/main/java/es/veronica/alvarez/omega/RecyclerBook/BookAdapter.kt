package es.veronica.alvarez.omega.RecyclerBook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.R

class BookAdapter(
    var listaLibros: List<LibroResponse> = emptyList(),
    private val onItemSelected: (LibroResponse) -> Unit
): RecyclerView.Adapter<BookViewHolder>() {

    fun updateBooks(list: List<LibroResponse>){
        listaLibros = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(listaLibros[position], onItemSelected)
    }

    override fun getItemCount() = listaLibros.size
}