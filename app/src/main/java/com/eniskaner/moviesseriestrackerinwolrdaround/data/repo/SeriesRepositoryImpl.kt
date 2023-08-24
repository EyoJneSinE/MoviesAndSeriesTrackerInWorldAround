package com.eniskaner.moviesseriestrackerinwolrdaround.data.repo

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_cast.CastingForSeriesFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_details.SeriesDetails
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_genre.SeriesGenreFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.series_video.GetSeriesTrailerFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.TopRatedTvFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.SeriesAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(private val seriesAPI: SeriesAPI): SeriesRepository {
    override suspend fun getSeriesFromTMDB(): TopRatedTvFromTMDB {
        return seriesAPI.getSeriesFromTMDB()
    }

    override suspend fun getSeriesDetailsFromTMDB(seriesId: Int): SeriesDetails {
        return seriesAPI.getSeriesDetailsFromTMDB(seriesId)
    }

    override suspend fun searchSerieFromTMDB(search: String): TopRatedTvFromTMDB {
        return seriesAPI.searchSeriesFromTMDB(search)
    }

    override suspend fun genreSerieFromTMDB(): SeriesGenreFromTMDB {
        return seriesAPI.genreSerieFromTMDB()
    }

    override suspend fun getTvVideosFromTMDB(seriesId: Int): GetSeriesTrailerFromTMDB {
        return seriesAPI.getTvVideosFromTMDB(seriesId)
    }

    override suspend fun getTopRatedSeriesFromTMDB(): TopRatedTvFromTMDB {
        return seriesAPI.getTopRatedTv()
    }

    override suspend fun getSeriesCastFromTMDB(seriesId: Int): CastingForSeriesFromTMDB {
        return seriesAPI.getTvCastFromTMDB(seriesId)
    }
}