package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.event

sealed class SeriesEvent {
    data class SearchSeries(val searchSeries: String) : SeriesEvent()
}
