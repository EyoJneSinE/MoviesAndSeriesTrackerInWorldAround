package com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_cast

data class CastingForSeriesFromTMDB(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int?
)