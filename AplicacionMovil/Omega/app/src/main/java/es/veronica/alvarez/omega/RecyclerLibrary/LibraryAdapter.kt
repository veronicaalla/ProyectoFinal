package es.veronica.alvarez.omega.RecyclerLibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.R


/**
 * Adapter para mostrar una lista de bibliotecas en un RecyclerView.
 *
 * @param listaBibliotecas La lista de bibliotecas que se mostrará en el RecyclerView.
 * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona una biblioteca.
 */
class LibraryAdapter (
    var listaBibliotecas: List<BibliotecaResponse> = emptyList(),
    private val onItemSelected : (BibliotecaResponse) -> Unit
): RecyclerView.Adapter<LibraryViewHolder>(){


    /**
     * Actualiza la lista de bibliotecas en el adaptador y notifica a RecyclerView para que se actualice.
     *
     * @param list La nueva lista de bibliotecas.
     */
    fun updateLibrary (list: List<BibliotecaResponse>){
        listaBibliotecas = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        return LibraryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_library, parent, false)
        )
    }


    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.bind(listaBibliotecas[position], onItemSelected)
    }


    override fun getItemCount() = listaBibliotecas.size

}