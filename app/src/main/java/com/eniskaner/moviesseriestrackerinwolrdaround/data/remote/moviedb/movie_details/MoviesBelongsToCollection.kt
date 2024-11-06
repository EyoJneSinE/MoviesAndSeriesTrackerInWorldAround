package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movie_details

import com.google.gson.annotations.SerializedName

data class MoviesBelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val id: Int?,
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)
