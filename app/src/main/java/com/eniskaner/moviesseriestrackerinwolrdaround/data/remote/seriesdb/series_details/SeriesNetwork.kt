package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName

data class SeriesNetwork(
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)
