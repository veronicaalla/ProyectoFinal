package es.veronica.alvarez.omega.RecyclerFavorito

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.R

class FavoritoAdapter(
    var listaGeneros: List<GeneroResponse> = emptyList(),
    private val onItemSelected: (GeneroResponse) -> Unit
) : RecyclerView.Adapter<FavoritoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        return FavoritoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        holder.bind(listaGeneros[position], onItemSelected)
    }

    override fun getItemCount() = listaGeneros.size
}