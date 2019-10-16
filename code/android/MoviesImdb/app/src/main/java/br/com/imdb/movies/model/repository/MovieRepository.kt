package br.com.imdb.movies.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.imdb.movies.APIKEY
import br.com.imdb.movies.model.domain.Cast
import br.com.imdb.movies.model.domain.Movie
import br.com.imdb.movies.model.domain.MoviesTitles
import br.com.imdb.movies.model.network.APIRetrofitService
import br.com.imdb.movies.model.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by pablo on 13/10/2019.
 */
class MovieRepository {


    private var repository: MovieRepository? = null
    private var movieAPi: MovieApi = APIRetrofitService.createService(MovieApi::class.java)

    //singleton
    fun getInstance(): MovieRepository {
        if (repository == null) {
            repository = MovieRepository()
        }
        return repository as MovieRepository
    }

    /**
     * get all top rated movies from API with livedata
     * example - http://api.themoviedb.org/3/movie/top_rated?api_key=API_KEY&language=en-US&page=20&region=US
     */
    fun getMoviesTopRated(): MutableLiveData<Movie> {
        val movieData = MutableLiveData<Movie>()
        movieAPi.getTopRatedMovies(APIKEY.TMDB_API_KEY, "en-US", 20, "US").enqueue(object :
            Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                if (response.isSuccessful) {
                    movieData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("response", t.printStackTrace().toString())
                movieData.value = null
            }
        })
        return movieData
    }

    /**
     * get all cast from movie
     */
    fun getMovieCasts(movieId: String): MutableLiveData<MoviesTitles> {
        val movieData = MutableLiveData<MoviesTitles>()
        movieAPi.getMovieCasts(movieId,APIKEY.TMDB_API_KEY ).enqueue(object :
            Callback<MoviesTitles> {
            override fun onResponse(
                call: Call<MoviesTitles>,
                response: Response<MoviesTitles>
            ) {
                Log.i("response", response.raw().request().url().toString())
                if (response.isSuccessful) {
                    movieData.value = response.body()
                }
            }

            override fun onFailure(call: Call<MoviesTitles>, t: Throwable) {
                Log.i("response", t.printStackTrace().toString())
                movieData.value = null
            }
        })
        return movieData
    }

}