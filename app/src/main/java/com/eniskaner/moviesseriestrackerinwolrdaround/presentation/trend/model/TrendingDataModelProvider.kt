package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.SeriesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.state.TrendingState
import javax.inject.Inject

class TrendingDataModelProvider @Inject constructor() {

    fun getTrendingData(
        moviesState: TrendingState,
        seriesState: TrendingState
    ): List<TrendingDataModel> {
        val trendingDataList = mutableListOf<TrendingDataModel>()
        val trendingHorizontalData = moviesState.trendingMovies.map { horizontalResult ->
            trendingHorizontalResult(horizontalResult)
        }

        trendingDataList.add(
            TrendingDataModel.TrendingHorizontal().apply {
                trendingHorizontal = trendingHorizontalData
            }
        )

        val moviesData = moviesState.trendingMovies.map { movieResult ->
            moviesResultToTrendingMovies(movieResult)
        }

        val seriesData = seriesState.trendingSeries.map { seriesResult ->
            seriesResultToTrendingSeries(seriesResult)
        }

        val totalItems = moviesData.size.coerceAtLeast(seriesData.size)

        if (moviesData.isNotEmpty() && seriesData.isNotEmpty()) {
            for (i in 0 until totalItems) {
                if (i < moviesData.size) {
                    trendingDataList.add(
                        TrendingDataModel.TrendingMoviesAndSeries(
                            movie = if (i < moviesData.size) {
                                moviesData[i]
                            } else null,
                            series = if (i < seriesData.size) {
                                seriesData[i]
                            } else null
                        )
                    )
                }
            }
        }

        return trendingDataList
    }

    private fun moviesResultToTrendingMovies(moviesResult: MoviesResult): TrendingDataModel.TrendingMovies {
        return TrendingDataModel.TrendingMovies(
            moviesPoster = moviesResult.posterPath ?: "",
            moviesTitle = moviesResult.title ?: "",
            moviesAirDate = moviesResult.releaseDate ?: ""
        )
    }

    private fun seriesResultToTrendingSeries(seriesResult: SeriesResult): TrendingDataModel.TrendingSeries {
        return TrendingDataModel.TrendingSeries(
            seriesPoster = seriesResult.posterPath ?: "",
            seriesTitle = seriesResult.name ?: "",
            seriesFirstAirDate = seriesResult.firstAirDate ?: ""
        )
    }

    private fun trendingHorizontalResult(trendingResult: MoviesResult): TrendingDataModel.TrendingHorizontalViewPager {
        return TrendingDataModel.TrendingHorizontalViewPager(
            horizontalViewPagerTitle = trendingResult.title ?: "",
            horizontalViewPagerPoster = trendingResult.backdropPath ?: ""
        )
    }
}