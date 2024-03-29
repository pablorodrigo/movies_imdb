package br.com.imdb.movies.model.network

import br.com.imdb.movies.model.domain.Movie
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



/**
 * Created by pablo on 13/10/2019.
 */
class APIRetrofitService {


    companion object {
        private const val BASE_URL = "http://api.themoviedb.org/3/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

        //conexion created
        private val gsonBuilder =
            GsonBuilder().registerTypeAdapter(Movie::class.java, MovieDeserializer()).create()

        //retrofit build
        private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .build()

        //create retrofit service
        fun <S> createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }

    }

}