package es.veronica.alvarez.omega.RecyclerLibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.R

class LibraryAdapter (
    var listaBibliotecas: List<BibliotecaResponse> = emptyList(),
    private val onItemSelected : (BibliotecaResponse) -> Unit
): RecyclerView.Adapter<LibraryViewHolder>(){

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