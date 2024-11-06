package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetNowPlayingMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetTopRatedSeriesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.state.TrendingState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrendingSeriesAndMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesForTrendingUseCase: GetNowPlayingMoviesUseCase,
    private val getTopRatedSeriesForTrendingUseCase: GetTopRatedSeriesUseCase
) : ViewModel() {

    private val _trendingMoviesState = MutableStateFlow(TrendingState())
    val trendingMoviesState = _trendingMoviesState.asStateFlow()

    private var jobTrendingMovies: Job? = null

    private val _trendingSeriesState = MutableStateFlow(TrendingState())
    val trendingSeriesState = _trendingSeriesState.asStateFlow()

    private var jobTrendingSeries: Job? = null

    init {
        getTrendingMovies()
        getTrendingSeries()
    }

    private fun getTrendingMovies() {
        jobTrendingMovies?.cancel()
        jobTrendingMovies =
            getNowPlayingMoviesForTrendingUseCase.executeGetNowPlayingMoviesFromTMDB().onEach {
                when (it) {
                    is Resource.Success -> {
                        _trendingMoviesState.value =
                            TrendingState(trendingMovies = it.data?.movies ?: emptyList())
                    }

                    is Resource.Error -> {
                        _trendingMoviesState.value = TrendingState(error = it.message ?: "Error!")
                    }

                    is Resource.Loading -> {
                        _trendingMoviesState.value = TrendingState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getTrendingSeries() {
        jobTrendingSeries?.cancel()
        jobTrendingSeries =
            getTopRatedSeriesForTrendingUseCase.executeGetTopRatedSeriesFromTMDB().onEach {
                when (it) {
                    is Resource.Success -> {
                        _trendingSeriesState.value =
                            TrendingState(trendingSeries = it.data?.series ?: emptyList())
                    }

                    is Resource.Error -> {
                        _trendingSeriesState.value = TrendingState(error = it.message ?: "Error!")
                    }

                    is Resource.Loading -> {
                        _trendingSeriesState.value = TrendingState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

}
