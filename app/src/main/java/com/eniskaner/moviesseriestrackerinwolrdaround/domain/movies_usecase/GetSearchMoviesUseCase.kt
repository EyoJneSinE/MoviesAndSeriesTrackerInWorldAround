package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val movieDetailsSearchRepository: MovieRepository
) {

    fun executeSearchMovieFromTMDB(search: String) : Flow<Resource<GetMovieFromId>> = flow {
        try {
            emit(Resource.Loading())
            val searchListFromTMDB = movieDetailsSearchRepository.searchMovieFromTMDB(search)
            if (searchListFromTMDB.movies.isNotEmpty()) {
                emit(Resource.Success(searchListFromTMDB))
            } else {
                emit(Resource.Error(message = "Searching Movie Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}