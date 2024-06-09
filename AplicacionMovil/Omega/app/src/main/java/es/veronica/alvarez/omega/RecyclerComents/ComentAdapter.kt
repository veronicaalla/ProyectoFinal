package es.veronica.alvarez.omega.RecyclerComents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.Model.LibroResponse
import es.veronica.alvarez.omega.R


/**
 * Adaptador para mostrar una lista de comentarios en un RecyclerView.
 *
 * @param listaComentarios La lista de comentarios a mostrar.
 * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona un comentario.
 */
class ComentAdapter(
    var listaComentarios : List<ComentarioResponse> = emptyList(),
    private val onItemSelected: (ComentarioResponse) -> Unit
) : RecyclerView.Adapter<ComentViewHolder>(){


    /**
     * Actualiza la lista de comentarios del adaptador.
     *
     * @param list La nueva lista de comentarios.
     */
    fun updateComents(list: List<ComentarioResponse>){
        listaComentarios = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentViewHolder {
        return ComentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComentViewHolder, position: Int) {
       holder.bind(listaComentarios[position], onItemSelected)
    }

    override fun getItemCount() = listaComentarios.size

}