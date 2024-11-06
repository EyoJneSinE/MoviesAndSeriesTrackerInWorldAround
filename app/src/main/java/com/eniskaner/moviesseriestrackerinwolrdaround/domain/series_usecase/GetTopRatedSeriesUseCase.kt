package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetTopRatedSeriesUseCase @Inject constructor(
    private val topRatedSeriesRepository: SeriesRepository
) {

    fun executeGetTopRatedSeriesFromTMDB(): Flow<Resource<TopRatedTvFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val topRatedTvListFromTMDB = topRatedSeriesRepository.getTopRatedSeriesFromTMDB()
            if (topRatedTvListFromTMDB.series.isNotEmpty()) {
                emit(Resource.Success(topRatedTvListFromTMDB))
            } else {
                emit(Resource.Error(message = "Now Playing Series Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

}
