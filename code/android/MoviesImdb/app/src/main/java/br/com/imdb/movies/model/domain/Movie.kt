package br.com.imdb.movies.model.domain

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by pablo on 13/10/2019.
 */
@Parcel
class Movie {

    @SerializedName("results")
    var listMovie: MutableList<MoviesTitles>? = null

}