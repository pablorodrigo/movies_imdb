package br.com.imdb.movies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.imdb.movies.R
import br.com.imdb.movies.model.domain.MoviesTitles
import br.com.imdb.movies.model.network.APIRetrofitService
import br.com.imdb.movies.util.MoviesUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movie.view.*
import java.util.*

/**
 * Created by pablo on 13/10/2019.
 */
class MovieAdapter(
    private val context: Context?,
    private val listMovie: List<MoviesTitles>,
    private val onClickListener: ((MoviesTitles) -> Unit)?
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(),
    Filterable {
    private var listMovieFilter: List<MoviesTitles>? = null

    init {
        this.listMovieFilter = listMovie
    }

    override fun getItemCount(): Int {
        return if (this.listMovieFilter != null) this.listMovieFilter!!.size else 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_movie, viewGroup, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        //update each item
        val movie = listMovieFilter!![position]

        with(holder.itemView) {

            tv_name_movie.text =
                movie.title!!.capitalize().plus(" (${movie.releaseDate?.substringBefore("-")})")

            release_date.text = MoviesUtil.dateFormat(movie.releaseDate)

            movie_rating.rating = movie.voteAverage!!.toFloat().div(2)

            progress_img_movie.visibility = View.VISIBLE

            //implementing button click for each item
            setOnClickListener {
                onClickListener?.invoke(movie)
            }

            //movie poster url
            val url = APIRetrofitService.BASE_URL_IMAGE.plus(movie.posterPath)

            //put image in cache
            Picasso.get().load(url).fetch()

            //load url
            Picasso.get().load(url).fit().into(imgv_movie,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        progress_img_movie.visibility = View.GONE // download ok
                    }

                    override fun onError(e: Exception) {
                        progress_img_movie.visibility = View.GONE
                    }
                })
        }


    }

    // ViewHolder with views
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {

                val charString = charSequence.toString()
                if (charString.isEmpty()) {

                    listMovieFilter = listMovie

                } else {

                    val listFilter = ArrayList<MoviesTitles>()

                    for (movie in listMovie) {

                        if (movie.title!!.contains(charString)) {

                            listFilter.add(movie)

                        }
                    }

                    listMovieFilter = listFilter
                }

                val filterResults = FilterResults()
                filterResults.values = listMovieFilter
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                listMovieFilter = filterResults.values as List<MoviesTitles>
                notifyDataSetChanged()
            }
        }
    }

    /**
     * filter recyclerview
     *
     * @param text
     */
    fun filter(text: String) {

        val textToFilter = text.toLowerCase(Locale.getDefault())

        if (textToFilter.isEmpty()) {

            listMovieFilter = listMovie

        } else {

            val listFilter = ArrayList<MoviesTitles>()

            for (movie in listMovie) {
                if (movie.title!!.toLowerCase(Locale.getDefault()).contains(textToFilter)) {

                    listFilter.add(movie)

                }
            }

            listMovieFilter = listFilter
        }
        notifyDataSetChanged()
    }

}

