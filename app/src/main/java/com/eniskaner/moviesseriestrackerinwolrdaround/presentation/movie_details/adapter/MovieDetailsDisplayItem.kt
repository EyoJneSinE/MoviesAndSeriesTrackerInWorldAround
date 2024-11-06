package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter

interface MovieDetailsDisplayItem {
    fun type(): Int

    companion object {
        const val TYPE_MOVIE_DETAILS = 0
        const val TYPE_MOVIE_CAST_LIST = 1
        const val TYPE_MOVIE_CREW_LIST = 2
        const val TYPE_MOVIE_TRAILER_LIST = 3
        const val TYPE_MOVIE_CAST = 4
        const val TYPE_MOVIE_CREW = 5
        const val TYPE_MOVIE_TRAILER = 6
    }
}