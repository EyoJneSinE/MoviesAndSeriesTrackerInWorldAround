package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.event

sealed class MoviesEvent {

    data class SearchMovies(val searchMovies: String) : MoviesEvent()

}
