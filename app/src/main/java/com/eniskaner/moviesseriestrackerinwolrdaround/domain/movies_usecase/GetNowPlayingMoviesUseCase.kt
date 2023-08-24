package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val nowPlayingMovieRepository: MovieRepository
) {
    fun executeGetNowPlayingMoviesFromTMDB() : Flow<Resource<GetMovieFromId>> = flow {
        try {
            emit(Resource.Loading())
            val nowPlayingListFromTMDB = nowPlayingMovieRepository.getNowPlayingMoviesFromTMDB()
            if (nowPlayingListFromTMDB.movies.isNotEmpty()) {
                emit(Resource.Success(nowPlayingListFromTMDB))
            } else {
                emit(Resource.Error(message = "Now Playing Movies Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}