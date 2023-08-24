package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MovieRepository
) {
    fun executeGetMoviesFromTMDB() : Flow<Resource<GetMovieFromId>> = flow {
        try {
            emit(Resource.Loading())
            val movieListFromTMDB = moviesRepository.getMoviesFromTMDB()
            if (movieListFromTMDB.movies.isNotEmpty()) {
                emit(Resource.Success(movieListFromTMDB))
            } else {
                emit(Resource.Error(message = "Movies Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}