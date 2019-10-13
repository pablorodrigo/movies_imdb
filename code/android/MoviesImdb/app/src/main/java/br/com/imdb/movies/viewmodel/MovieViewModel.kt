package br.com.imdb.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.imdb.movies.model.domain.Movie
import br.com.imdb.movies.model.repository.MovieRepository


/**
 * Created by pablo on 13/10/2019.
 */
class MovieViewModel : ViewModel() {


    private var mutableLiveData: MutableLiveData<Movie>? = null
    private var  movieRepository: MovieRepository = MovieRepository().getInstance()

    init {
        mutableLiveData = movieRepository.getMoviesTopRated()
    }


    fun geMoviesTopRatedLiveData(): LiveData<Movie>? {
        return mutableLiveData
    }

}