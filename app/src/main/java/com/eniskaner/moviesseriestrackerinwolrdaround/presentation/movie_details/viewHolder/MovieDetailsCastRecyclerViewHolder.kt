package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerviewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsCastAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails

class MovieDetailsCastRecyclerViewHolder(
    private val binding: MovieDetailsCastRecyclerviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovieDetailsCastRecyclerview(item: MovieDetails.MovieDetailsCastList) {
        binding.movieDetailsCastRV.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        init(item)
    }

    fun init(item : MovieDetails.MovieDetailsCastList) {
        binding.movieDetailsCastRV.adapter = MovieDetailsCastAdapter().apply {
            submitList(item.movieCastList)
        }
    }
}