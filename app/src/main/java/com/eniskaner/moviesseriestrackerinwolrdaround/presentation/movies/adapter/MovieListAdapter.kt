package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MoviesRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model.NowPlayingMovies
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model.NowPlayingMovies.*
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class MovieListAdapter(private val onItemClick: (Movies) -> Unit) : ListAdapter<Movies, MovieListAdapter.MovieViewHolder>(MovieListDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MoviesRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindMovie(movie)
    }

    inner class MovieViewHolder(private  val movieBinding: MoviesRecyclerRowBinding): RecyclerView.ViewHolder(movieBinding.root) {
        init {
            movieBinding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = getItem(position)
                }
            }
        }
        fun bindMovie(movie: NowPlayingMovies.Movies) {
            val genreText = movie.nowPlayingMoviesGenre.joinToString(",\n")
            movieBinding.apply {
                moviesGenreTypeTextView.text = genreText
                moviesReleaseDateTextView.text = movie.nowPlayingMoviesReleaseDate
                moviesOriginalTitleTextView.text = movie.nowPlayingMoviesTitle
                moviesVerticalPoster.load(Constants.POSTER_URL + movie.nowPlayingMoviesPoster)
            }
        }
    }
}

