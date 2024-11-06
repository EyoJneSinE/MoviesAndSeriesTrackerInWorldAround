package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_cast

import com.google.gson.annotations.SerializedName

data class Crew(
    @SerializedName("adult")
    val seriesCrewAdult: Boolean?,
    @SerializedName("credit_id")
    val seriesCrewCreditId: String?,
    @SerializedName("department")
    val seriesCrewDepartment: String?,
    @SerializedName("gender")
    val seriesCrewGender: Int?,
    @SerializedName("id")
    val seriesCrewId: Int?,
    @SerializedName("job")
    val seriesCrewJob: String?,
    @SerializedName("known_for_department")
    val seriesCrewKnownForDepartment: String?,
    @SerializedName("name")
    val seriesCrewName: String?,
    @SerializedName("original_name")
    val seriesCrewOriginalName: String?,
    @SerializedName("popularity")
    val seriesCrewPopularity: Double?,
    @SerializedName("profile_path")
    val seriesCrewProfilePath: String?
)
