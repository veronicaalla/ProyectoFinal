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

        binding = FragmentStartAppBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val toolbar: Toolbar = binding.toolbar
        //(activity as AppCompatActivity).setSupportActionBar(toolbar)


        binding.menuChat.setOnClickListener {

        }

        binding.menuNotificaciones.setOnClickListener {

        }


    }


}