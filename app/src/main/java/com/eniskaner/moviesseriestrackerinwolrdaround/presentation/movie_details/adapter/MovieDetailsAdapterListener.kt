package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

interface MovieDetailsAdapterListener {
    fun onTrailerClick(trailer: MovieDetails.GetMovieDetailsTrailer?)
}
