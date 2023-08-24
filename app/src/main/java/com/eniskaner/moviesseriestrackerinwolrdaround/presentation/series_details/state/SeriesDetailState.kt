package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.state

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_cast.CastingForSeriesFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_cast.Crew
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details.SeriesDetails
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenreFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_video.SeriesTrailerResut

data class SeriesDetailState(
    val isLoading : Boolean = false,
    val seriesDetails : SeriesDetails? = null,
    val seriesCast : CastingForSeriesFromTMDB? = null,
    val seriesCrew : List<Crew> = emptyList(),
    val seriesGenre : SeriesGenreFromTMDB? = null,
    val seriesTrailer : List<SeriesTrailerResut> = emptyList(),
    val error : String = ""
)
