package es.veronica.alvarez.omega.RecyclerComents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.ComentResponse
import es.veronica.alvarez.omega.R

class ComentAdapter(
    var listaComentarios : List<ComentResponse> = emptyList()
) : RecyclerView.Adapter<ComentViewHolder>(){

    fun updateComents(list: List<ComentResponse>){
        listaComentarios = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentViewHolder {
        return ComentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_coment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComentViewHolder, position: Int) {
       holder.bind(listaComentarios[position])
    }

    override fun getItemCount() = listaComentarios.size

}