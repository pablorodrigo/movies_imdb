package br.com.imdb.movies.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.imdb.movies.R
import br.com.imdb.movies.model.domain.MoviesTitles
import br.com.imdb.movies.view.adapter.MovieAdapter
import br.com.imdb.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.parceler.Parcels


class MainActivity : BaseActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        configUI()

    }

    /**
     * click function for eatch item of Movie in recyclerview
     * @param movie object send to another view
     */
    private fun onClickListener(movie: MoviesTitles) {

        startActivity<MovieDetailActivity>("movie" to Parcels.wrap(movie))

    }

    /**
     * config MainActivity UI
     */
    private fun configUI() {
        //config recycler
        recycler_movies.layoutManager = LinearLayoutManager(this)
        recycler_movies.itemAnimator = DefaultItemAnimator()
        recycler_movies.setHasFixedSize(true)

    }

    /**
     * update user ui
     */
    private fun updateUI() {

        movieViewModel.geMoviesTopRatedLiveData()?.observe(this, Observer {

            adapter = MovieAdapter(this, it.listMovie!!) { movie -> onClickListener(movie) }
            recycler_movies.adapter = adapter

        })
    }


    /**
     * load default menu for activity
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)

        //config menuSearch
        val search = menu?.findItem(R.id.menu_item_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = this.resources.getString(R.string.ic_search)
        search(searchView)

        return super.onCreateOptionsMenu(menu)

    }

    /**
     * filter list of Movies in view
     * @param
     */
    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (adapter != null) adapter!!.filter(newText)
                return true
            }
        })
    }


    override fun onResume() {
        super.onResume()
        updateUI()
    }

}
