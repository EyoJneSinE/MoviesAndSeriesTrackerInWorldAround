package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter

interface SeriesDisplayItem {
    fun type(): Int

    companion object {
        const val TYPE_SERIES = 0
    }
}