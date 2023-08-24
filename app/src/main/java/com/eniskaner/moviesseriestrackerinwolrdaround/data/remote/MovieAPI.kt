package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.GetTrailerFromMovieId
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.THE_MOVIEDB_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/trending/movie/day")
    suspend fun getMoviesFromTMDB(
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GetMovieFromId

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetailFromTMDB(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GetMovieDetailsFromId

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMoviesFromTMDB(
        @Query("api_key") apiKey: String= THE_MOVIEDB_KEY
    ): GetMovieFromId

    @GET("/3/search/movie")
    suspend fun searchMovieFromTMDB(
        @Query("query") query:String?,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GetMovieFromId

    @GET("/3/genre/movie/list")
    suspend fun genreMovieFromTMDB(
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GenresFromTMDB

    @GET("/3/movie/{movieId}/videos")
    suspend fun getVideosFromTMDB(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): GetTrailerFromMovieId

    @GET("/3/movie/{movieId}/credits")
    suspend fun getCastFromTMDB(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String=THE_MOVIEDB_KEY
    ): CastingForMovieFromTMDB

}