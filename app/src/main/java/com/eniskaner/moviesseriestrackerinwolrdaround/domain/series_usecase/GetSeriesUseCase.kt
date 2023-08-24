package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.GetMovieFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {
    fun executeGetSeriesFromTMDB() : Flow<Resource<TopRatedTvFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val serieListFromTMDB = seriesRepository.getSeriesFromTMDB()
            if (serieListFromTMDB.series.isNotEmpty()) {
                emit(Resource.Success(serieListFromTMDB))
            } else {
                emit(Resource.Error(message = "Series Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}