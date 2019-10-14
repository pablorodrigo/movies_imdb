package br.com.imdb.movies.model.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by pablo on 13/10/2019.
 */
@Parcel
class MoviesTitles {

    var title: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("vote_average")
    var voteAverage: String? = null
    var overview: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    var adult: Boolean? = null
    @SerializedName("vote_count")
    var voteCount: String? = null

}