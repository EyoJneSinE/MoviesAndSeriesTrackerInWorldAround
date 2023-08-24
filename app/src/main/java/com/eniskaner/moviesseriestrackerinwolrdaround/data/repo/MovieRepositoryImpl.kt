package com.eniskaner.moviesseriestrackerinwolrdaround.data.repo

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.GetTrailerFromMovieId
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.MovieAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieAPI): MovieRepository {
    override suspend fun getMoviesFromTMDB(): GetMovieFromId {
        return movieApi.getMoviesFromTMDB()
    }

    override suspend fun getMovieDetailsFromTMDB(movieId: Int): GetMovieDetailsFromId {
        return movieApi.getMovieDetailFromTMDB(movieId)
    }

    override suspend fun searchMovieFromTMDB(search: String): GetMovieFromId {
        return movieApi.searchMovieFromTMDB(search)
    }

    override suspend fun genreMovieFromTMDB(): GenresFromTMDB {
        return movieApi.genreMovieFromTMDB()
    }

    override suspend fun getVideosFromTMDB(movieId: Int): GetTrailerFromMovieId {
        return movieApi.getVideosFromTMDB(movieId)
    }

    override suspend fun getNowPlayingMoviesFromTMDB(): GetMovieFromId {
        return movieApi.getNowPlayingMoviesFromTMDB()
    }

    override suspend fun getCastFromTMDB(movieId: Int): CastingForMovieFromTMDB {
        return movieApi.getCastFromTMDB(movieId)
    }
}