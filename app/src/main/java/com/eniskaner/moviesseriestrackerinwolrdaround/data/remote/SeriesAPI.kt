package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_cast.CastingForSeriesFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details.SeriesDetails
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenreFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_video.GetSeriesTrailerFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesAPI {
    @GET("/3/trending/tv/day")
    suspend fun getSeriesFromTMDB(
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): TopRatedTvFromTMDB

    @GET("/3/tv/{seriesId}")
    suspend fun getSeriesDetailsFromTMDB(
        @Path("seriesId") seriesId: Int,
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): SeriesDetails

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTv(
        @Query("api_key") apikey: String= Constants.MOVIEDB_KEY
    ): TopRatedTvFromTMDB

    @GET("/3/search/tv")
    suspend fun searchSeriesFromTMDB(
        @Query("query") query: String?,
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): TopRatedTvFromTMDB

    @GET("/3/genre/tv/list")
    suspend fun genreSerieFromTMDB(
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): SeriesGenreFromTMDB

    @GET("/3/tv/{seriesId}/videos")
    suspend fun getTvVideosFromTMDB(
        @Path("seriesId") seriesId: Int,
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): GetSeriesTrailerFromTMDB

    @GET("/3/tv/{seriesId}/credits")
    suspend fun getTvCastFromTMDB(
        @Path("seriesId") seriesId: Int,
        @Query("api_key") apiKey: String= Constants.MOVIEDB_KEY
    ): CastingForSeriesFromTMDB
}