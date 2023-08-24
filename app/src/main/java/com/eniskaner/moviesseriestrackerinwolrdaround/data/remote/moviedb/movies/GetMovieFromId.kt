package com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies

import com.google.gson.annotations.SerializedName

data class GetMovieFromId(
    val page: Int?,
    @SerializedName("results")
    val movies: List<MoviesResult>,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)