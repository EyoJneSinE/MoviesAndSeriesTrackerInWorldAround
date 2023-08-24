package com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    val name: String?
)