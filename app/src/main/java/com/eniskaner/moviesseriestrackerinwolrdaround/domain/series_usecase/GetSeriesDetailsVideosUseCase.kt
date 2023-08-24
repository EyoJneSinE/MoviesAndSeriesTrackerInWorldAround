package com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.GetTrailerFromMovieId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_video.GetSeriesTrailerFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetSeriesDetailsVideosUseCase @Inject constructor(
    private val seriesDetailsVideoRepository: SeriesRepository
) {
    fun executeGetSeriesVideosFromTMDB(seriesId: Int) : Flow<Resource<GetSeriesTrailerFromTMDB>> = flow {
        try {
            emit(Resource.Loading())
            val getSeriesTrailerFromTMDB = seriesDetailsVideoRepository.getTvVideosFromTMDB(seriesId)
            if (getSeriesTrailerFromTMDB.results.isNotEmpty()) {
                emit(Resource.Success(getSeriesTrailerFromTMDB))
            } else {
                emit(Resource.Error(message = "Series Trailers Can't Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Internet connection error!!!"))
        }
    }
}