package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetTopRatedSeriesUseCase
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
class TrendingSeriesPartViewModel @Inject constructor(
    private val getTopRatedSeriesForTrendingUseCase: GetTopRatedSeriesUseCase
) : ViewModel() {

    private val _trendingSeriesState = MutableStateFlow<TrendingState>(TrendingState())
    val trendingSeriesState : StateFlow<TrendingState> = _trendingSeriesState

    private var jobTrendingSeries : Job? = null

    init {
        getTrendingSeries()
    }

    private fun getTrendingSeries() {
        jobTrendingSeries?.cancel()
        jobTrendingSeries = getTopRatedSeriesForTrendingUseCase.executeGetTopRatedSeriesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _trendingSeriesState.value = TrendingState(trendingSeries = it.data?.series ?: emptyList())
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