package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails

class MovieDetailsDiffCallBack: DiffUtil.ItemCallback<MovieDetails>() {
    override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return when {
            (oldItem is MovieDetails.GetMovieDetailsFromId && newItem is MovieDetails.GetMovieDetailsFromId) -> {
                oldItem.movieDetailsGenre == newItem.movieDetailsGenre ||
                        oldItem.movieDetailsOverview == newItem.movieDetailsOverview ||
                        oldItem.movieDetailsTagline == newItem.movieDetailsTagline ||
                        oldItem.movieDetailsHorizontalPoster == newItem.movieDetailsHorizontalPoster ||
                        oldItem.movieDetailsOriginalTitle == newItem.movieDetailsOriginalTitle ||
                        oldItem.movieDetailsReleaseDate == newItem.movieDetailsReleaseDate ||
                        oldItem.movieDetailsVerticalPoster == newItem.movieDetailsVerticalPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCast && newItem is MovieDetails.GetMovieDetailsCast) -> {
                oldItem.movieDetailsCastName == newItem.movieDetailsCastName ||
                        oldItem.movieDetailsCastCharacterName == newItem.movieDetailsCastCharacterName ||
                        oldItem.movieDetailsCastPoster == newItem.movieDetailsCastPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsCrew && newItem is MovieDetails.GetMovieDetailsCrew) -> {
                oldItem.movieDetailsCrewName == newItem.movieDetailsCrewName ||
                        oldItem.movieDetailsCrewJob == newItem.movieDetailsCrewJob ||
                        oldItem.movieDetailsCrewPoster == newItem.movieDetailsCrewPoster
            }
            (oldItem is MovieDetails.GetMovieDetailsTrailer && newItem is MovieDetails.GetMovieDetailsTrailer) -> {
                oldItem.movieDetailsTrailerKey == newItem.movieDetailsTrailerKey
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
        return oldItem == newItem
    }
}