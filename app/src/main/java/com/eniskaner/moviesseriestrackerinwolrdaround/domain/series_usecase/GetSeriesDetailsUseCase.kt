package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_details.SeriesDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSeriesDetailsUseCase @Inject constructor(
    private val seriesDetailsRepository: SeriesRepository
) {
    fun executeGetSeriesDetailsFromTMDB(seriesId: Int): Flow<Resource<SeriesDetails>> = flow {
        try {
            emit(Resource.Loading())
            val getSeriesDetailsFromTMDB =
                seriesDetailsRepository.getSeriesDetailsFromTMDB(seriesId)
            if (getSeriesDetailsFromTMDB.seasons.isNotEmpty() || getSeriesDetailsFromTMDB.genres.isNotEmpty() || getSeriesDetailsFromTMDB.languages.isNotEmpty()) {
                emit(Resource.Success(getSeriesDetailsFromTMDB))
            } else {
                emit(Resource.Error(message = "Series Details Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}
