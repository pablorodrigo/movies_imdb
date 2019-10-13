package br.com.imdb.movies.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.imdb.movies.APIKEY
import br.com.imdb.movies.model.domain.Movie
import br.com.imdb.movies.model.network.APIRetrofitService
import br.com.imdb.movies.model.network.MovieApi
import br.com.imdb.movies.model.network.MovieDeserializer
import br.com.imdb.movies.viewmodel.MovieViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.imdb.movies.R.layout.activity_main)

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    /**
     * update user ui
     */
    private fun updateUI() {

        movieViewModel.geMoviesTopRatedLiveData()?.observe(this, Observer {

            it.listMovie?.let { movie ->
                tv_test.text = movie[19].title
            }

        })
    }
}
