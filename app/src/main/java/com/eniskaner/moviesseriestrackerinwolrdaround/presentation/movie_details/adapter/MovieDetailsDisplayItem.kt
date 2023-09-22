package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

interface MovieDetailsDisplayItem {
    fun type(): Int

    companion object {
        const val TYPE_MOVIE_DETAILS = 0
        const val TYPE_MOVIE_CAST = 1
        const val TYPE_MOVIE_CREW = 2
        const val TYPE_MOVIE_TRAILER = 3
    }
}