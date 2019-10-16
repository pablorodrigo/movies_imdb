package br.com.imdb.movies.view.activity

import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.imdb.movies.R
import br.com.imdb.movies.model.domain.MoviesTitles
import br.com.imdb.movies.model.network.APIRetrofitService
import br.com.imdb.movies.util.MoviesUtil
import br.com.imdb.movies.view.adapter.CastAdapter
import br.com.imdb.movies.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.parceler.Parcels

class MovieDetailActivity : BaseActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private var adapter: CastAdapter? = null
    private lateinit var movie: MoviesTitles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpToolbar()

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movie = Parcels.unwrap<MoviesTitles>(intent.getParcelableExtra<Parcelable>("movie"))

        configUI()
        updateUI(movie)
    }

    private fun configUI() {

        //config recycler
        detail_cast_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        detail_cast_recycler_view.itemAnimator = DefaultItemAnimator()
        detail_cast_recycler_view.setHasFixedSize(true)


        //config imate button
        add_to_favourite.setOnClickListener {

            it.setBackgroundResource(R.drawable.ic_thumb_up_white_24dp)

        }
    }

    private fun updateUI(movie: MoviesTitles) {


        loadImage(APIRetrofitService.BASE_URL_IMAGE.plus(movie.posterPath), detail_poster_image)
        loadImage(APIRetrofitService.BASE_URL_IMAGE.plus(movie.backdropPath), detail_backdrop_image)

        //fill views
        detail_movie_title.text = movie.title
        detail_movie_date.text = MoviesUtil.dateFormat(movie.releaseDate)
        detail_adult.text = getString(R.string.adult, movie.adult.toString())
        detail_vote_average.text = getString(R.string.rating, movie.voteAverage)
        detail_vote_count.text = getString(R.string.votes, movie.voteCount)
        detail_overview.text = movie.overview
        detail_rating_bar.rating = movie.voteAverage!!.toFloat().div(2)

        loadCasts()

    }

    private fun loadCasts() {

        movie.id?.let { movieId ->

            movieViewModel.getMovieCastsLiveData(movieId)?.observe(this, Observer {

                adapter = it.listCast?.let { listCast ->
                    CastAdapter(this, listCast)
                }
                detail_cast_recycler_view.adapter = adapter

            })
        }


    }
}
