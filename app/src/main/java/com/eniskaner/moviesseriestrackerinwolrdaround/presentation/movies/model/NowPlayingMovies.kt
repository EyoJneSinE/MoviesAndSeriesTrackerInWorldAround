package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter.MovieDisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter.MovieDisplayItem.Companion.TYPE_MOVIES

sealed class NowPlayingMovies : MovieDisplayItem {
    data class Movies(
        val nowPlayingMoviesTitle: String,
        val nowPlayingMoviesGenre: List<String?>,
        val nowPlayingMoviesReleaseDate: String,
        val nowPlayingMoviesPoster: String
    ): NowPlayingMovies(), MovieDisplayItem {
        override fun type(): Int {
            return TYPE_MOVIES
        }
    }
}
