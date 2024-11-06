package com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_cast.CastingForSeriesFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_details.SeriesDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_genre.SeriesGenreFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_video.GetSeriesTrailerFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB

interface SeriesRepository {

    suspend fun getSeriesFromTMDB(): TopRatedTvFromTMDB

    suspend fun getSeriesDetailsFromTMDB(seriesId: Int): SeriesDetails

    suspend fun searchSerieFromTMDB(search: String): TopRatedTvFromTMDB

    suspend fun genreSerieFromTMDB(): SeriesGenreFromTMDB

    suspend fun getTvVideosFromTMDB(seriesId: Int): GetSeriesTrailerFromTMDB

    suspend fun getTopRatedSeriesFromTMDB(): TopRatedTvFromTMDB

    suspend fun getSeriesCastFromTMDB(seriesId: Int): CastingForSeriesFromTMDB

}
