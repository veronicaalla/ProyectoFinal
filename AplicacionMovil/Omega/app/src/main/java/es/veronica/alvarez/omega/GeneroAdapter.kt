package es.veronica.alvarez.omega

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView
import es.veronica.alvarez.omega.Model.GeneroResponse


/**
 * Adaptador personalizado para el spinner de géneros.
 * Permite mostrar una lista de géneros en un spinner.
 *
 * @param context Contexto de la aplicación.
 * @param generos Lista de géneros a mostrar en el spinner.
 */
class GeneroAdapter(context: Context, generos: List<GeneroResponse>) : ArrayAdapter<GeneroResponse>(context, android.R.layout.simple_spinner_item, generos) {

    init {
        // Establecer el diseño para las opciones desplegables
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }


    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        // Obtener la vista del elemento actual
        val view = super.getView(position, convertView, parent) as TextView
        // Establecer el texto de la vista como el nombre del género actual
        view.text = getItem(position)?.nombre
        return view
    }

    override fun getDropDownView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        // Obtener la vista del elemento actual en la lista desplegable
        val view = super.getDropDownView(position, convertView, parent) as TextView
        // Establecer el texto de la vista como el nombre del género actual
        view.text = getItem(position)?.nombre
        return view
    }
}
