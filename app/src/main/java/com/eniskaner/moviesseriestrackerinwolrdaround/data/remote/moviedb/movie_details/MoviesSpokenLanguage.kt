package com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details

import com.google.gson.annotations.SerializedName

data class MoviesSpokenLanguage(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    val name: String?
)