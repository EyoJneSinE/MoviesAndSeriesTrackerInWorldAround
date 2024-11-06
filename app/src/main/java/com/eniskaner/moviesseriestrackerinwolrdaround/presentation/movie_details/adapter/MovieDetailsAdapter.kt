package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerviewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerviewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsTrailerChildItemBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CAST_LIST
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_CREW_LIST
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_DETAILS
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_TRAILER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsDisplayItem.Companion.TYPE_MOVIE_TRAILER_LIST
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCastRecyclerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCastViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCrewRecyclerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCrewViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsTrailerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsViewHolder

class MovieDetailsAdapter(
    private val trailerAdapterListener: MovieDetailsAdapterListener
) : ListAdapter<MovieDetails, RecyclerView.ViewHolder>(MovieDetailsDiffCallBack()) {

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
                    MovieDetailsCastRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCastRecyclerViewHolder(movieDetailsCastBinding)
            }

            TYPE_MOVIE_CAST_LIST -> {
                val movieDetailsCastBinding =
                    MovieDetailsCastRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCastRecyclerViewHolder(movieDetailsCastBinding)
            }

            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewBinding =
                    MovieDetailsCrewRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCrewRecyclerViewHolder(movieDetailsCrewBinding)
            }

            TYPE_MOVIE_CREW_LIST -> {
                val movieDetailsCrewBinding =
                    MovieDetailsCrewRecyclerviewBinding.inflate(layoutInflater, parent, false)
                MovieDetailsCrewRecyclerViewHolder(movieDetailsCrewBinding)
            }

            TYPE_MOVIE_TRAILER -> {
                val movieDetailsTrailerBinding =
                    MovieDetailsTrailerChildItemBinding.inflate(layoutInflater, parent, false)
                MovieDetailsTrailerViewHolder(movieDetailsTrailerBinding, trailerAdapterListener)
            }

            TYPE_MOVIE_TRAILER_LIST -> {
                val movieDetailsTrailerBinding =
                    MovieDetailsTrailerChildItemBinding.inflate(layoutInflater, parent, false)
                MovieDetailsTrailerViewHolder(movieDetailsTrailerBinding, trailerAdapterListener)
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

            TYPE_MOVIE_CAST_LIST -> {
                val movieDetailsCastHolder = holder as MovieDetailsCastRecyclerViewHolder
                movieDetailsCastHolder.bindMovieDetailsCastRecyclerview(item as MovieDetails.MovieDetailsCastList)
            }

            TYPE_MOVIE_CREW_LIST -> {
                val movieDetailsCrewHolder = holder as MovieDetailsCrewRecyclerViewHolder
                movieDetailsCrewHolder.bindMovieDetailsCrewRecyclerview(item as MovieDetails.MovieDetailsCrewList)
            }

            TYPE_MOVIE_CAST -> {
                val movieDetailsCastHolder = holder as MovieDetailsCastRecyclerViewHolder
                movieDetailsCastHolder.bindMovieDetailsCastRecyclerview(item as MovieDetails.MovieDetailsCastList)
            }

            TYPE_MOVIE_CREW -> {
                val movieDetailsCrewHolder = holder as MovieDetailsCrewRecyclerViewHolder
                movieDetailsCrewHolder.bindMovieDetailsCrewRecyclerview(item as MovieDetails.MovieDetailsCrewList)
            }

            TYPE_MOVIE_TRAILER_LIST -> {
                val movieDetailsTrailerHolder = holder as MovieDetailsTrailerViewHolder
                movieDetailsTrailerHolder.bindMovieDetailsTrailer(item as MovieDetails.MovieDetailsTrailerList)
            }

            TYPE_MOVIE_TRAILER -> {
                val movieDetailsTrailerHolder = holder as MovieDetailsTrailerViewHolder
                movieDetailsTrailerHolder.bindMovieDetailsTrailer(item as MovieDetails.MovieDetailsTrailerList)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).type()

}
