package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state.MoviesState
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state.SeriesState
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
class SeriesViewModel @Inject constructor(
    private val getSeriesUseCase: GetSeriesUseCase
) : ViewModel() {

    private val _stateSeries = MutableStateFlow(SeriesState())
    val stateSeries = _stateSeries.asStateFlow()

    private var jobSeries: Job? = null

    init {
        getSeries()
    }

    private fun getSeries() {
        jobSeries?.cancel()
        jobSeries = getSeriesUseCase.executeGetSeriesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeries.value = SeriesState(series = it.data?.series ?: emptyList())
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
