package es.veronica.alvarez.omega.RecyclerComents

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.Model.ComentarioResponse
import es.veronica.alvarez.omega.databinding.ItemComentBinding


class ComentViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemComentBinding.bind(view)

    fun bind (comentarioResponse: ComentarioResponse){
        binding.txtUsuario.text = "Usuario"
    }
}

