package br.com.imdb.movies.model.network

import br.com.imdb.movies.model.domain.Movie
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by pablo on 13/10/2019.
 */
class MovieDeserializer : JsonDeserializer<Movie> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Movie {
        var element = json?.asJsonObject

        if (json?.asJsonObject != null)
            element = json.asJsonObject


        return Gson().fromJson(element, Movie::class.java)
    }

}