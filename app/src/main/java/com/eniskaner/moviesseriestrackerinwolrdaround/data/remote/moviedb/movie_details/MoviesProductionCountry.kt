package com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details

import com.google.gson.annotations.SerializedName

data class MoviesProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    val name: String?
)