package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCastViewHolder

class MovieDetailsCastAdapter: ListAdapter<MovieDetails.GetMovieDetailsCast, MovieDetailsCastViewHolder>(MovieDetailsCastDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_details_cast_recycler_row,
            parent,
            false
        )
        return MovieDetailsCastViewHolder(MovieDetailsCastRecyclerRowBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: MovieDetailsCastViewHolder, position: Int) {
        holder.bindMovieDetailsCast(getItem(position) as MovieDetails.GetMovieDetailsCast)
    }
}