package com.eniskaner.moviesseriestrackerinwolrdaround.data.di

import android.content.Context
import com.eniskaner.moviesseriestrackerinwolrdaround.MASApplication
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.MovieAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.SeriesAPI
import com.eniskaner.moviesseriestrackerinwolrdaround.data.repo.MovieRepositoryImpl
import com.eniskaner.moviesseriestrackerinwolrdaround.data.repo.SeriesRepositoryImpl
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.MovieRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.repo.SeriesRepository
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.THE_MOVIEDB_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieAPI = Retrofit.Builder()
        .baseUrl(THE_MOVIEDB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieAPI: MovieAPI
    ): MovieRepository = MovieRepositoryImpl(movieApi = movieAPI)


    @Singleton
    @Provides
    fun provideSeriesApi(): SeriesAPI = Retrofit.Builder()
        .baseUrl(THE_MOVIEDB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SeriesAPI::class.java)

    @Singleton
    @Provides
    fun provideSeriesRepository(
        seriesAPI: SeriesAPI
    ): SeriesRepository {
        return SeriesRepositoryImpl(seriesAPI = seriesAPI)
    }
}
