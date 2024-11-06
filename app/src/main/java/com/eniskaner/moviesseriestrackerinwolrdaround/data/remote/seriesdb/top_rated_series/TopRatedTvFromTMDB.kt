package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series

import com.google.gson.annotations.SerializedName

data class TopRatedTvFromTMDB(
    val page: Int,
    @SerializedName("results")
    val series: List<SeriesResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
