package es.veronica.alvarez.omega

import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import es.veronica.alvarez.omega.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflamos la vista
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtenemos la ruta del video
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/raw/videointro")
        binding.inicioApp.setVideoURI(uri)
        //Iniciamos el video
        binding.inicioApp.start()


        //Obtenemos el sonido, y le iniciamos
        val sonidoIntro = MediaPlayer.create(this, R.raw.sonidointro)
        sonidoIntro.start()

        Handler(Looper.getMainLooper()).postDelayed({
            //Al ser la ventana inicial, donde el usuario no tiene que interactuar
            //Sino que solamente debe de esperar, le quitaremos la animacion que hay
            //Entre ventanas
            val flagIntent = Intent(this, MainActivity::class.java)
            flagIntent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(flagIntent)

        }, 3000)

    }
}