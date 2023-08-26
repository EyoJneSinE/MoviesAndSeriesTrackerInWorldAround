package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetNowPlayingMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.state.TrendingState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesPartViewModel @Inject constructor(
    private val getNowPlayingMoviesForTrendingUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _trendingMoviesState = MutableStateFlow<TrendingState>(TrendingState())
    val trendingMoviesState : StateFlow<TrendingState> = _trendingMoviesState

    private var jobTrendingMovies : Job? = null

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        jobTrendingMovies?.cancel()
        jobTrendingMovies = getNowPlayingMoviesForTrendingUseCase.executeGetNowPlayingMoviesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _trendingMoviesState.value = TrendingState(trendingMovies = it.data?.movies ?: emptyList())
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
}