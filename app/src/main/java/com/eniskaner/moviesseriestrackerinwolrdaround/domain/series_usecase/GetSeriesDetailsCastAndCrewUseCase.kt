package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_cast.CastingForSeriesFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSeriesDetailsCastAndCrewUseCase @Inject constructor(
    private val seriesCastAndCrewRepository: SeriesRepository
) {

    fun executeGetSeriesCastFromTMDB(seriesId: Int) : Flow<Resource<CastingForSeriesFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val getSeriesCastFromTMDB = seriesCastAndCrewRepository.getSeriesCastFromTMDB(seriesId)
            if (getSeriesCastFromTMDB.cast.isNotEmpty() || getSeriesCastFromTMDB.crew.isNotEmpty()) {
                emit(Resource.Success(getSeriesCastFromTMDB))
            } else {
                emit(Resource.Error(message = "Cast And Crew Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }

}
