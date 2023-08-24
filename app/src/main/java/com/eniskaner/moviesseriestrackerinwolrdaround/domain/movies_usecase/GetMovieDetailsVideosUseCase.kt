package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.GetTrailerFromMovieId
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsVideosUseCase @Inject constructor(
    private val movieDetailsVideoRepository: MovieRepository
) {
    fun executeGetVideosFromTMDB(movieId: Int) : Flow<Resource<GetTrailerFromMovieId>> = flow {
        try {
            emit(Resource.Loading())
            val getTrailerFromTMDB = movieDetailsVideoRepository.getVideosFromTMDB(movieId)
            if (getTrailerFromTMDB.results.isNotEmpty()) {
                emit(Resource.Success(getTrailerFromTMDB))
            } else {
                emit(Resource.Error(message = "Movie Trailers Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}