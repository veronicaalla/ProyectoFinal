package es.veronica.alvarez.omega.RecyclerBook

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.BookResponse
import es.veronica.alvarez.omega.databinding.ItemBookBinding

class BookViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemBookBinding.bind(view)

    fun bind(
        bookResponse : BookResponse,
        onItemSelected: (BookResponse) -> Unit
    ){
        //binding.txtTitulo.text = bookResponse
    }
}