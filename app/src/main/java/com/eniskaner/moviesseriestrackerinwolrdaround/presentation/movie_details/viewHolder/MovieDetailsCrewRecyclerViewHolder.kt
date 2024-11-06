package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerviewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerviewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsCastAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsCrewAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails

class MovieDetailsCrewRecyclerViewHolder(
    private val binding: MovieDetailsCrewRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovieDetailsCrewRecyclerview(item: MovieDetails.MovieDetailsCrewList) {
        binding.movieDetailsCrewRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        init(item)
    }

    fun init(item : MovieDetails.MovieDetailsCrewList) {
        binding.movieDetailsCrewRV.adapter = MovieDetailsCrewAdapter().apply {
            submitList(item.movieCrewList)
        }
    }
}