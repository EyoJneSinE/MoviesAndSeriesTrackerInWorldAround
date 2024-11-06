package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast

import com.google.gson.annotations.SerializedName

data class MovieCast(
    @SerializedName("adult")
    val moviesCastAdult: Boolean?,
    @SerializedName("cast_id")
    val moviesCastCastId: Int?,
    @SerializedName("character")
    val moviesCastCharacter: String?,
    @SerializedName("credit_id")
    val moviesCastCreditId: String?,
    @SerializedName("gender")
    val moviesCastGender: Int?,
    @SerializedName("id")
    val moviesCastId: Int?,
    @SerializedName("know_for_department")
    val moviesCastKnownForDepartment: String?,
    @SerializedName("name")
    val moviesCastName: String?,
    @SerializedName("order")
    val moviesCastOrder: Int?,
    @SerializedName("original_name")
    val moviesCastOriginalName: String?,
    @SerializedName("popularity")
    val moviesCastPopularity: Double?,
    @SerializedName("profile_path")
    val moviesCastProfilePath: String?
)
