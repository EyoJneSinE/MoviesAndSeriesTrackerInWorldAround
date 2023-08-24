package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.state

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.SeriesResult

data class TrendingState(
    val isLoading: Boolean = false,
    val movies: List<MoviesResult> = emptyList(),
    val series: List<SeriesResult> = emptyList(),
    val error: String = "",
)
