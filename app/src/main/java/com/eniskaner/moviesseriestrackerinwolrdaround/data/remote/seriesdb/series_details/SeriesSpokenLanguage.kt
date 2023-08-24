package com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesSpokenLanguage(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    val name: String?
)