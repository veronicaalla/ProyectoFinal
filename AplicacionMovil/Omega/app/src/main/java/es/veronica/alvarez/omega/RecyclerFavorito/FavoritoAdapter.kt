package es.veronica.alvarez.omega.RecyclerFavorito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.R


/**
 * Adaptador para mostrar una lista de géneros favoritos en un RecyclerView.
 *
 * @param listaGeneros La lista de géneros a mostrar.
 * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona un género.
 */
class FavoritoAdapter(
    var listaGeneros: List<GeneroResponse> = emptyList(),
    private val onItemSelected: (GeneroResponse) -> Unit
) : RecyclerView.Adapter<FavoritoViewHolder>() {


    /**
     * Crea y devuelve una instancia de FavoritoViewHolder.
     *
     * @param parent El ViewGroup en el que se inflará la vista de elemento.
     * @param viewType El tipo de la vista, que se puede utilizar para proporcionar un diseño diferente.
     * @return Una nueva instancia de FavoritoViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        return FavoritoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genero, parent, false)
        )
    }


    /**
     * Vincula los datos de un género a la vista.
     *
     * @param holder El FavoritoViewHolder que contendrá los datos del género.
     * @param position La posición del género en la lista.
     */
    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        holder.bind(listaGeneros[position], onItemSelected)
    }


    /**
     * Devuelve el número total de elementos en la lista de géneros.
     *
     * @return El número total de elementos en la lista de géneros.
     */
    override fun getItemCount() = listaGeneros.size

}