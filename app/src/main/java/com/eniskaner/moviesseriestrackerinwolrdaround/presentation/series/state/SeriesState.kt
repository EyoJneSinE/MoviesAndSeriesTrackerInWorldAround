package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenre
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB

data class SeriesState (
    val isLoading : Boolean = false,
    val series : TopRatedTvFromTMDB? = null,
    val seriesGenre: List<SeriesGenre> = emptyList(),
    val error : String = "",
    val search : String = "the"
)