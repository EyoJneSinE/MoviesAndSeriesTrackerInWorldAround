package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_cast

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("adult")
    val seriesCastAdult: Boolean?,
    @SerializedName("character")
    val seriesCastCharacter: String?,
    @SerializedName("credit_id")
    val seriesCastCreditId: String?,
    @SerializedName("gender")
    val seriesCastGender: Int?,
    @SerializedName("id")
    val seriesCastId: Int?,
    @SerializedName("known_for_department")
    val seriesCastKnownForDepartment: String?,
    @SerializedName("name")
    val seriesCastName: String?,
    @SerializedName("order")
    val seriesCastOrder: Int?,
    @SerializedName("orifinal_name")
    val seriesCastOriginalName: String?,
    @SerializedName("popularity")
    val seriesCastPopularity: Double?,
    @SerializedName("profile_path")
    val seriesCastProfilePath: String?
)
