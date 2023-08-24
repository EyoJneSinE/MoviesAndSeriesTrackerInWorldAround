package com.eniskaner.moviesseriestrackerinwolrdaround.data.di

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.MovieAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.SeriesAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.data.repo.MovieRepositoryImpl
import com.eniskaner.moviesseriestrackerinwolrdaround.data.repo.SeriesRepositoryImpl
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.THE_MOVIEDB_URL
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieAPI {
        return Retrofit.Builder()
            .baseUrl(THE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieAPI: MovieAPI) : MovieRepository {
        return MovieRepositoryImpl(movieApi = movieAPI)
    }

    @Provides
    @Singleton
    fun provideSeriesApi() : SeriesAPI {
        return Retrofit.Builder()
            .baseUrl(THE_MOVIEDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SeriesAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideSeriesRepository(seriesAPI: SeriesAPI) : SeriesRepository {
        return SeriesRepositoryImpl(seriesAPI = seriesAPI)
    }
}