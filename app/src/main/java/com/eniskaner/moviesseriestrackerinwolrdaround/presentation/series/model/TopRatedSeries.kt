package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter.SeriesDisplayItem

sealed class TopRatedSeries : SeriesDisplayItem {

    data class Series(
        val topRatedSeriesId: Int,
        val topRatedSeriesTitle: String,
        val topRatedSeriesGenre: List<String?>,
        val topRatedSeriesFirstAirDate: String,
        val topRatedSeriesPoster: String
    ) : TopRatedSeries(), SeriesDisplayItem {
        override fun type(): Int {
            return SeriesDisplayItem.TYPE_SERIES
        }
    }

}
