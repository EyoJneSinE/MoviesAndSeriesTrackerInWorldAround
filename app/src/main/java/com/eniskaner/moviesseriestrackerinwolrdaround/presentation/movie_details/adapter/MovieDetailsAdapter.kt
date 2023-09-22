package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsTrailerChildItemBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_DETAILS
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_TRAILER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCastViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCrewViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsViewHolder

class MovieDetailsAdapter(private val onItemClick: (MovieDetails.GetMovieDetailsTrailer) -> Unit) : ListAdapter<MovieDetails, RecyclerView.ViewHolder>(MovieDetailsDiffCallBack())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_MOVIE_DETAILS -> {
                val movieDetailsBinding =
                    MovieDetailsRecyclerRowBinding.inflate(layoutInflater, parent, false)
                MovieDetailsViewHolder(movieDetailsBinding)
            }
            TYPE_MOVIE_CAST -> {
                val movieDetailsCastBinding =
                    MovieDetailsCastRecyclerRowBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCastViewHolder(movieDetailsCastBinding)
            }
            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewBinding =
                    MovieDetailsCrewRecyclerRowBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCrewViewHolder(movieDetailsCrewBinding)
            }
            TYPE_MOVIE_TRAILER -> {
                val movieDetailsTrailerBinding =
                    MovieDetailsTrailerChildItemBinding.inflate(layoutInflater, parent, false)
                MovieDetailsTrailerViewHolder(movieDetailsTrailerBinding)
            }
            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.type()) {
            TYPE_MOVIE_DETAILS -> {
                val movieDetailsHolder = holder as MovieDetailsViewHolder
                movieDetailsHolder.bindMovieDetails(item as MovieDetails.GetMovieDetailsFromId)
            }
            TYPE_MOVIE_CAST -> {
                val movieDetailsCastHolder = holder as MovieDetailsCastViewHolder
                movieDetailsCastHolder.bindMovieDetailsCast(item as MovieDetails.GetMovieDetailsCast)
            }
            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewHolder = holder as MovieDetailsCrewViewHolder
                movieDetailsCrewHolder.bindMovieDetailsCast(item as MovieDetails.GetMovieDetailsCrew)
            }
            TYPE_MOVIE_TRAILER -> {
                val movieDetailsTrailerHolder = holder as MovieDetailsTrailerViewHolder
                movieDetailsTrailerHolder.bindMovieDetailsTrailer()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is MovieDetails.GetMovieDetailsFromId -> TYPE_MOVIE_DETAILS
            is MovieDetails.GetMovieDetailsCast -> TYPE_MOVIE_CAST
            is MovieDetails.GetMovieDetailsCrew -> TYPE_MOVIE_CREW
            is MovieDetails.GetMovieDetailsTrailer -> TYPE_MOVIE_TRAILER
            else -> throw IllegalArgumentException("Invalid Type")
        }
    }
    inner class MovieDetailsTrailerViewHolder(private val movieDetailsTrailerBinding: MovieDetailsTrailerChildItemBinding
    ): RecyclerView.ViewHolder(movieDetailsTrailerBinding.root) {

        init {
            movieDetailsTrailerBinding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = getItem(position)
                    val movieType = getItem(position).type()
                    if (movieType == 3) {
                        onItemClick(movie as MovieDetails.GetMovieDetailsTrailer)
                    }

                }
            }
        }

        fun bindMovieDetailsTrailer() {

        }
    }
}