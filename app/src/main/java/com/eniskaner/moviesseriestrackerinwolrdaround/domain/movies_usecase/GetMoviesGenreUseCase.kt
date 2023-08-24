package com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesGenreUseCase @Inject constructor(
    private val movieDetailsGenreRepository: MovieRepository
) {
    fun executeGenreMovieFromTMDB() : Flow<Resource<GenresFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val genreListFromTMDB = movieDetailsGenreRepository.genreMovieFromTMDB()
            if (genreListFromTMDB.genres.isNotEmpty()) {
                emit(Resource.Success(genreListFromTMDB))
            } else {
                emit(Resource.Error(message = "Movie Genres Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}