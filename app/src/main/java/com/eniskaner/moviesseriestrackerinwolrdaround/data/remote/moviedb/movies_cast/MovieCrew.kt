package com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast

import com.google.gson.annotations.SerializedName

data class MovieCrew(
    @SerializedName("adult")
    val moviesCrewAdult: Boolean?,
    @SerializedName("credit_id")
    val moviesCrewCreditId: String?,
    @SerializedName("department")
    val moviesCrewDepartment: String?,
    @SerializedName("gender")
    val moviesCrewGender: Int?,
    @SerializedName("id")
    val moviesCrewId: Int?,
    @SerializedName("job")
    val moviesCrewJob: String?,
    @SerializedName("known_for_department")
    val moviesCrewKnownForDepartment: String?,
    @SerializedName("name")
    val moviesCrewName: String?,
    @SerializedName("original_name")
    val moviesCrewOriginalName: String?,
    @SerializedName("popularity")
    val moviesCrewPopularity: Double?,
    @SerializedName("profile_path")
    val moviesCrewProfilePath: String?
)