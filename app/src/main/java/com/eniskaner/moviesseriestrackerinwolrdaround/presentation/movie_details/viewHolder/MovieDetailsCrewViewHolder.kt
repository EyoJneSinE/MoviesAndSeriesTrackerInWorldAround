package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsCrewRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class MovieDetailsCrewViewHolder(private val movieDetailsCrewBinding: MovieDetailsCrewRecyclerRowBinding
): RecyclerView.ViewHolder(movieDetailsCrewBinding.root) {
    fun bindMovieDetailsCrew(movieDetailsCastItem: MovieDetails.GetMovieDetailsCrew) {
        movieDetailsCrewBinding.apply {
            movieDetailsCrewNameTextView.text = movieDetailsCastItem.movieDetailsCrewName
            movieDetailsCrewJobContentsTextView.text = movieDetailsCastItem.movieDetailsCrewJob
            moviesCrewMemberPoster.load(Constants.POSTER_URL + movieDetailsCastItem.movieDetailsCrewPoster)
        }
    }
}