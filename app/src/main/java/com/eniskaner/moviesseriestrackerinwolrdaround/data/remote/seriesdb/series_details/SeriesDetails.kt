package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_details

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SeriesDetails(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("created_by")
    val createdBy: List<SeriesCreatedBy>,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val genres: List<SeriesDetailsGenre>,
    val homepage: String?,
    val id: Int?,
    @SerializedName("in_production")
    val inProduction: Boolean?,
    val languages: List<String>,
    @SerializedName("last_air_date")
    val lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: SeriesLastEpisodeToAir,
    val name: String?,
    val networks: List<SeriesNetwork>,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: SeriesNextEpisodeToAir,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<SeriesProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<SeriesProductionCountry>,
    val seasons: List<SeriesSeason>,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SeriesSpokenLanguage>,
    val status: String?,
    val tagline: String?,
    val type: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) : Serializable
