package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state.MoviesState
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state.SeriesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase
): ViewModel() {

    private val _stateSeries = MutableStateFlow<SeriesState>(SeriesState())
    val stateSeries : StateFlow<SeriesState> = _stateSeries

    private var jobSeries : Job? = null

    init {
        getSeries()
    }

    private fun getSeries() {
        jobSeries?.cancel()
        jobSeries = getSeriesUseCase.executeGetSeriesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeries.value = SeriesState(series = it.data ?: null)
                }

                is Resource.Error -> {
                    _stateSeries.value = SeriesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateSeries.value = SeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}