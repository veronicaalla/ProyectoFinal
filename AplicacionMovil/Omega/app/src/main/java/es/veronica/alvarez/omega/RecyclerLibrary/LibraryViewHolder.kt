package es.veronica.alvarez.omega.RecyclerLibrary

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.databinding.ItemLibraryBinding

class LibraryViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLibraryBinding.bind(view)

    fun bind (
        bibliotecaResponse : BibliotecaResponse,
        onItemSelected : (BibliotecaResponse) -> Unit
    ){
        binding.nombreBiblioteca.text = bibliotecaResponse.nombre

        binding.switchPublica.setChecked(bibliotecaResponse.publica)
        binding.switchPublica.setClickable(false)
        binding.switchPublica.setFocusable(false)

        binding.root.setOnClickListener { onItemSelected(bibliotecaResponse) }
    }
}