package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesSeason(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int?,
    val id: Int?,
    val name: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)
