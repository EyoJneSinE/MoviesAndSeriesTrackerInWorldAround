package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_video

data class GetSeriesTrailerFromTMDB(
    val id: Int?,
    val results: List<SeriesTrailerResut>
)
