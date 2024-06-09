package es.veronica.alvarez.omega.RecyclerLibrary

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.databinding.ItemLibraryBinding


/**
 * ViewHolder para mostrar una biblioteca en un RecyclerView.
 *
 * @param view La vista raíz del elemento de la biblioteca.
 */
class LibraryViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLibraryBinding.bind(view)


    /**
     * Vincula los datos de la biblioteca especificada al ViewHolder.
     *
     * @param bibliotecaResponse Los datos de la biblioteca que se mostrarán.
     * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona la biblioteca.
     */
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