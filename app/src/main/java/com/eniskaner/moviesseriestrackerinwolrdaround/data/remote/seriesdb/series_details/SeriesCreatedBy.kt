package com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesCreatedBy(
    @SerializedName("credit_id")
    val creditId: String?,
    val gender: Int?,
    val id: Int?,
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?
)