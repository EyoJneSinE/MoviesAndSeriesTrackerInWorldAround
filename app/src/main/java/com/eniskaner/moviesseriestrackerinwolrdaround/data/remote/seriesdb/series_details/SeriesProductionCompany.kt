package com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesProductionCompany(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)