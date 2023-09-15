package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetTopRatedSeriesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state.SeriesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TopRatedSeriesViewModel @Inject constructor(
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesUseCase
): ViewModel() {

    private val _stateTopRatedSeries = MutableStateFlow<SeriesState>(SeriesState())
    val stateTopRatedSeries : StateFlow<SeriesState> = _stateTopRatedSeries

    private var jobTopRatedSeries : Job? = null

    init {
        getTopRatedSeries()
    }

    private fun getTopRatedSeries() {
        jobTopRatedSeries?.cancel()
        jobTopRatedSeries = getTopRatedSeriesUseCase.executeGetTopRatedSeriesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateTopRatedSeries.value = SeriesState(topRatedSeries = it.data?.series ?: emptyList())
                }

                is Resource.Error -> {
                    _stateTopRatedSeries.value = SeriesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateTopRatedSeries.value = SeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}