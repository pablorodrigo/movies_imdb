package br.com.imdb.movies.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.include_toolbar.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by pablo on 13/10/2019.
 * generic class for activitys to share functions
 */
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity(),AnkoLogger {

    /**
     * Tool bar configuration
     */
    fun setUpToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

}