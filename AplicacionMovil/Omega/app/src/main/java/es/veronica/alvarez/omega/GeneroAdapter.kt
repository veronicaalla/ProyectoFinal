package es.veronica.alvarez.omega

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.TextView
import es.veronica.alvarez.omega.Model.GeneroResponse

class GeneroAdapter(context: Context, generos: List<GeneroResponse>) : ArrayAdapter<GeneroResponse>(context, android.R.layout.simple_spinner_item, generos) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        val view = super.getView(position, convertView, parent) as TextView
        view.text = getItem(position)?.nombre
        return view
    }

    override fun getDropDownView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        val view = super.getDropDownView(position, convertView, parent) as TextView
        view.text = getItem(position)?.nombre
        return view
    }
}
