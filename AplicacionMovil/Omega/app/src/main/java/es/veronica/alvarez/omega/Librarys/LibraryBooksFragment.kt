package es.veronica.alvarez.omega.Librarys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.veronica.alvarez.omega.Model.BibliotecaResponse
import es.veronica.alvarez.omega.Model.PerfilUsuarioResponse
import es.veronica.alvarez.omega.R


class LibraryBooksFragment : Fragment() {

    private lateinit var biblioteca: BibliotecaResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_books, container, false)
    }

    //Debemos recoger el valor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            biblioteca = it.getSerializable("biblioteca") as BibliotecaResponse
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}