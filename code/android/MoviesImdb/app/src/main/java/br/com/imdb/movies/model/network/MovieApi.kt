package br.com.imdb.movies.model.network

import br.com.imdb.movies.model.domain.Movie
import br.com.imdb.movies.model.domain.MoviesTitles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pablo on 13/10/2019.
 */
interface MovieApi {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") pageNumber: Int,
        @Query("region") region: String
    ): Call<Movie>

    @GET
    fun getDetailMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("movie_id") movieId: String
    ): Call<MoviesTitles>
}