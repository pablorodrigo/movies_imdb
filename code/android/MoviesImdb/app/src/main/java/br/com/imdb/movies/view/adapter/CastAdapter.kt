package br.com.imdb.movies.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.imdb.movies.R
import br.com.imdb.movies.model.domain.Cast
import br.com.imdb.movies.model.network.APIRetrofitService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_cast.view.*

/**
 * Created by pablo on 15/10/2019.
 */
class CastAdapter(private val context: Context, private val listCast: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_cast, viewGroup, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        // Atualiza a view
        val cast = listCast[position]
        val view = holder.itemView

        with(view) {
            movie_cast_character.text = cast.character
            movie_cast_name.text = cast.name


            val url = APIRetrofitService.BASE_URL_IMAGE.plus(cast.profilePath)

            //load url
            Picasso.get().load(url).fit().into(movie_cast_image,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception) {
                        movie_cast_image.setImageResource(R.drawable.no_image)
                    }
                })
        }

    }

    override fun getItemCount(): Int {
        return this.listCast.size
    }

    // ViewHolder with import Android Kotlin Extensions
    class CastViewHolder(view: View) : RecyclerView.ViewHolder(view)


}

