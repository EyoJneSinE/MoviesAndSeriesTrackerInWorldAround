package com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_video.GetTrailerFromMovieId

interface MovieRepository {

    suspend fun getMoviesFromTMDB(): GetMovieFromId

    suspend fun getMovieDetailsFromTMDB(movieId: Int): GetMovieDetailsFromId

    suspend fun searchMovieFromTMDB(search: String): GetMovieFromId

    suspend fun genreMovieFromTMDB(): GenresFromTMDB

    suspend fun getVideosFromTMDB(movieId: Int): GetTrailerFromMovieId

    suspend fun getNowPlayingMoviesFromTMDB(): GetMovieFromId

    suspend fun getCastFromTMDB(movieId: Int): CastingForMovieFromTMDB

}
