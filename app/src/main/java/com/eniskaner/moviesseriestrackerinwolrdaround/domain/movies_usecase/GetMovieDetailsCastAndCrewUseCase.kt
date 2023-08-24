package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsCastAndCrewUseCase @Inject constructor(
    private val moviesCastAndCrewRepository: MovieRepository
) {
    fun executeGetCastAndCrewFromTMDB(movieId: Int) : Flow<Resource<CastingForMovieFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val getCastFromTMDB = moviesCastAndCrewRepository.getCastFromTMDB(movieId)
            if (getCastFromTMDB.cast.isNotEmpty() || getCastFromTMDB.crew.isNotEmpty()) {
                emit(Resource.Success(getCastFromTMDB))
            } else {
                emit(Resource.Error(message = "Movies Cast Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}