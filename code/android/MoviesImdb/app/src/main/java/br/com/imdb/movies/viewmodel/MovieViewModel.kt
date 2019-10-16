package br.com.imdb.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.imdb.movies.model.domain.Cast
import br.com.imdb.movies.model.domain.Movie
import br.com.imdb.movies.model.domain.MoviesTitles
import br.com.imdb.movies.model.repository.MovieRepository


/**
 * Created by pablo on 13/10/2019.
 */
class MovieViewModel : ViewModel() {


    private var mutableLiveDataMovie: MutableLiveData<Movie>? = null
    private var mutableLiveDataMovieTitles: MutableLiveData<MoviesTitles>? = null
    private var movieRepository: MovieRepository = MovieRepository().getInstance()

    init {
        mutableLiveDataMovie = movieRepository.getMoviesTopRated()
    }


    fun geMoviesTopRatedLiveData(): LiveData<Movie>? {
        return mutableLiveDataMovie
    }

    fun getMovieCastsLiveData(movieId: String): LiveData<MoviesTitles>? {
        if (mutableLiveDataMovieTitles == null)
            mutableLiveDataMovieTitles = movieRepository.getMovieCasts(movieId)
        return mutableLiveDataMovieTitles
    }

}