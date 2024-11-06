package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder.MovieDetailsCrewViewHolder

class MovieDetailsCrewAdapter: ListAdapter<MovieDetails.GetMovieDetailsCrew, MovieDetailsCrewViewHolder>(MovieDetailsCrewDiffCallBack()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieDetailsCrewViewHolder {
        val binding = MovieDetailsCrewRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieDetailsCrewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieDetailsCrewViewHolder, position: Int) {
        holder.bindMovieDetailsCrew(getItem(position) as MovieDetails.GetMovieDetailsCrew)
    }

}