package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenreFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSeriesGenreUseCase @Inject constructor(
    private val seriesDetailsGenreRepository: SeriesRepository
) {
    fun executeGenreSeriesFromTMDB() : Flow<Resource<SeriesGenreFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val genreSeriesListFromTMDB = seriesDetailsGenreRepository.genreSerieFromTMDB()
            if (genreSeriesListFromTMDB.genres.isNotEmpty()) {
                emit(Resource.Success(genreSeriesListFromTMDB))
            } else {
                emit(Resource.Error(message = "Series Genre Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}