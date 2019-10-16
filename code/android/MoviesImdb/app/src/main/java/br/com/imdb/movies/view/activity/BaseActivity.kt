package br.com.imdb.movies.view.activity

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.imdb.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by pablo on 13/10/2019.
 * generic class for activitys to share functions
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), AnkoLogger {

    /**
     * Tool bar configuration
     */
    fun setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }


    fun loadImage(url: String, imageView: ImageView) {
        //load url
        Picasso.get().load(url).fit().into(imageView,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception) {
                    imageView.setImageResource(R.drawable.no_image)
                }
            })
    }

}