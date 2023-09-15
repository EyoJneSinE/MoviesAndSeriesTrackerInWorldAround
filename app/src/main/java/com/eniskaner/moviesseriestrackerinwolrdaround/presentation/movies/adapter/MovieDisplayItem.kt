package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter

interface MovieDisplayItem {

    fun type(): Int

    companion object {
        const val TYPE_MOVIES = 0
    }
}