package com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details

import com.google.gson.annotations.SerializedName

data class MoviesProductionCompany(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)