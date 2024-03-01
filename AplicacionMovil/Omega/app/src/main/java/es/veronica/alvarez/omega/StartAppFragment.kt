package es.veronica.alvarez.omega

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.veronica.alvarez.omega.databinding.FragmentStartAppBinding


class StartAppFragment : Fragment() {

    private lateinit var binding: FragmentStartAppBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_start_app, container, false)
        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root

        // opcionesMenu()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val toolbar: Toolbar = binding.toolbar
        //(activity as AppCompatActivity).setSupportActionBar(toolbar)


        binding.menuChat.setOnClickListener {

        }

        binding.menuNotificaciones.setOnClickListener {

        }


        val listaPost = binding.listaPost
        //Creamos los valores
        val valores = arrayOf(
            "C",
            "Java",
            "C++",
            "Python",
            "Per1",
            "PHP",
            "Haskell",
            "Eiffel",
            "Lisp",
            "Pascal",
            "Cobol",
            "Swift",
            "Kotlin",
            "Eiffel",
            "Lisp",
            "Pascal",
            "Cobol",
            "Swift",
            "Kotlin"
        )


        val adaptador = ArrayAdapter(
            requireActivity(), R.layout.simple_list_item_1, valores
        )
        //Le asignamos a la lista, el adaptador creado
        listaPost.adapter = adaptador


        //---------------   El valor i = posicion   ---------------

        listaPost.setOnItemClickListener { adapterView, view, i, l ->
            val elemento = adaptador.getItem(i) as String

            Toast.makeText(context, "$elemento seleccionado", Toast.LENGTH_LONG).show()
        }

        /* binding.bottomNavigationView.setOnNavigationItemSelectedListener {
             when(it.itemId){
                 R.id.opc
             }
         }*/

    }


}