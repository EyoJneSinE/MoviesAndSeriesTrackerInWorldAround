package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

interface DisplayItem {
    fun type(): Int

    companion object {
        /*const val TYPE_TRENDING_HORIZONTAL = 0*/
        const val TYPE_TRENDING_HORIZONTAL_VIEW_PAGER = 0
        const val TYPE_TRENDING_MOVIES_AND_SERIES = 1
        const val TYPE_TRENDING_MOVIE = 2
        const val TYPE_TRENDING_SERIES = 3
    }
}

