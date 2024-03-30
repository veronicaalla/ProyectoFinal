package es.veronica.alvarez.omega.RecyclerLibrary

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.databinding.ItemLibraryBinding

class LibraryViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLibraryBinding.bind(view)

    fun bind (
        libraryResponse : LibraryResponse,
        onItemSelected : (LibraryResponse) -> Unit
    ){
        binding.nombreBiblioteca.text = "Principal"
    }
}