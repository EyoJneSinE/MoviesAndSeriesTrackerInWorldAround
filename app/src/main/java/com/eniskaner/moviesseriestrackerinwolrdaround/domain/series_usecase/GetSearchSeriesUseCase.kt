package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSearchSeriesUseCase @Inject constructor(
    private val seriesSearchRepository: SeriesRepository
) {

    fun executeSearchSerieFromTMDB(search: String): Flow<Resource<TopRatedTvFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val searchSeriesListFromTMDB = seriesSearchRepository.searchSerieFromTMDB(search)
            if (searchSeriesListFromTMDB.series.isNotEmpty()) {
                emit(Resource.Success(searchSeriesListFromTMDB))
            } else {
                emit(Resource.Error(message = "Searching Series Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

}
