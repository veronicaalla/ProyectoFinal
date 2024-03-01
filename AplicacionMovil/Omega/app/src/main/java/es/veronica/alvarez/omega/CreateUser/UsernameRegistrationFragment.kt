package es.veronica.alvarez.omega.CreateUser

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import es.veronica.alvarez.omega.R
import es.veronica.alvarez.omega.databinding.FragmentUsernameRegistrationBinding


class UsernameRegistrationFragment : Fragment() {

    private val REQUEST_IMAGE_GET = 1
    private lateinit var binding: FragmentUsernameRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsernameRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBuscarFoto.setOnClickListener {
            openGallery()
        }

        binding.btnContinuar.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_usernameRegistrationFragment_to_passwordRegistrationFragment)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_GET)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == REQUEST_IMAGE_GET && data != null) {
            val selectedImage = data.data
            binding.imageView.setImageURI(selectedImage)

        }
    }

}