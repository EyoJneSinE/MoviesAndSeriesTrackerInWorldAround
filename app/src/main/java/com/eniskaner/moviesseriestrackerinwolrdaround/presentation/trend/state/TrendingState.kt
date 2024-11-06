package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.state

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series.SeriesResult

data class TrendingState(
    val isLoading: Boolean = false,
    val trendingMovies: List<MoviesResult> = emptyList(),
    val trendingSeries: List<SeriesResult> = emptyList(),
    val error: String = "",
)
