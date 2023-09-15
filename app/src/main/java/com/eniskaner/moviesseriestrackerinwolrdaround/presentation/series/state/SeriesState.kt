package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenre
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.SeriesResult
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB

data class SeriesState (
    val isLoading : Boolean = false,
    val series : List<SeriesResult> = emptyList(),
    val searchingSeries : List<SeriesResult> = emptyList(),
    val topRatedSeries : List<SeriesResult> = emptyList(),
    val seriesGenre: List<SeriesGenre> = emptyList(),
    val error : String = "",
    val search : String = "the"
)