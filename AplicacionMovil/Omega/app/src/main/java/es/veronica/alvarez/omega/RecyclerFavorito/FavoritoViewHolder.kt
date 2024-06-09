package es.veronica.alvarez.omega.RecyclerFavorito

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import es.veronica.alvarez.omega.CreateUser.UsuarioViewModel
import es.veronica.alvarez.omega.Model.GeneroResponse
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.UserPreferences
import es.veronica.alvarez.omega.databinding.ItemGeneroBinding


/**
 * ViewHolder para mostrar un género en un RecyclerView de géneros favoritos.
 *
 * @param view La vista que representa el elemento del RecyclerView.
 */
class FavoritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemGeneroBinding.bind(view)
    private val preferenciasUsuario = UserPreferences(view.context)


    /**
     * Vincula los datos de un género a la vista del ViewHolder.
     *
     * @param genero El objeto GeneroResponse que contiene los datos del género.
     * @param onItemSelected La función de devolución de llamada que se llama cuando se selecciona un género.
     */
    fun bind(genero: GeneroResponse, onItemSelected: (GeneroResponse) -> Unit) {
        binding.nombreGenero.text = genero.nombre

        // Establecer el ícono inicial basado en si el género está en la lista de favoritos o no
        val esFavorito = preferenciasUsuario.generosFavoritos.contains(genero.id)
        binding.imgIcono.setImageResource(if (esFavorito) R.drawable.icono_favorito else R.drawable.icono_no_fav)

        binding.imgIcono.setOnClickListener {
            // Obtener la lista actual de géneros favoritos
            val generosFavoritos = preferenciasUsuario.generosFavoritos.toMutableSet()

            if (esFavorito) {
                // Si el género ya es favorito, eliminarlo de la lista y cambiar el ícono a no favorito
                generosFavoritos.remove(genero.id)
                binding.imgIcono.setImageResource(R.drawable.icono_no_fav)
            } else {
                // Si el género no es favorito, agregarlo a la lista y cambiar el ícono a favorito
                generosFavoritos.add(genero.id)
                binding.imgIcono.setImageResource(R.drawable.icono_favorito)
            }

            // Actualizar la lista de géneros favoritos en UserPreferences
            preferenciasUsuario.generosFavoritos = generosFavoritos
        }
    }
}
