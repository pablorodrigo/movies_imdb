package br.com.imdb.movies.model.domain

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by pablo on 15/10/2019.
 */
@Parcel
class Cast {

    var character: String? = null
    var name: String? = null
    @SerializedName("profile_path")
    var profilePath: String? = null

}