package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_genre.MoviesGenre

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<MoviesResult> = emptyList(),
    val moviesSearching: List<MoviesResult> = emptyList(),
    val moviesNowPlaying: List<MoviesResult> = emptyList(),
    val moviesGenre: List<MoviesGenre> = emptyList(),
    val error: String = "",
    val search: String = "marvel"
)
