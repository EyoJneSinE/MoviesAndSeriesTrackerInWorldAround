package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCastRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.POSTER_URL
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class MovieDetailsCastViewHolder(private val movieDetailsCastBinding: MovieDetailsCastRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCastBinding.root) {
    fun bindMovieDetailsCast(movieDetailsCastItem: MovieDetails.GetMovieDetailsCast) {
        movieDetailsCastBinding.apply {
            movieDetailsCastNameTextView.text = movieDetailsCastItem.movieDetailsCastName
            movieDetailsCastCharacterContentsTextView.text = movieDetailsCastItem.movieDetailsCastCharacterName
            moviesCastMemberPoster.load(POSTER_URL + movieDetailsCastItem.movieDetailsCastPoster)
        }
    }
}