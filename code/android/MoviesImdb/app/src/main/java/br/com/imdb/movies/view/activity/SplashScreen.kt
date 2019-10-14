package br.com.imdb.movies.view.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.imdb.movies.R
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //wait some secound and start another activity
        val handle = Handler()
        handle.postDelayed({
            startActivity<MainActivity>()
            finish()
        }, 1000)
    }
}
