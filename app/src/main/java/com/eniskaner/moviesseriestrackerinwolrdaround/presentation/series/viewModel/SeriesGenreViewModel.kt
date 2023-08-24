package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesGenreUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.state.SeriesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SeriesGenreViewModel @Inject constructor(
    private val getSeriesGenreUseCase: GetSeriesGenreUseCase
): ViewModel() {
    private val _stateSeriesGenre = MutableStateFlow<SeriesState>(SeriesState())
    val stateSeriesGenre : StateFlow<SeriesState> = _stateSeriesGenre

    private var jobSerieGenres : Job? = null

    init {
        getSeriesGenre()
    }

    private fun getSeriesGenre() {
        jobSerieGenres?.cancel()
        jobSerieGenres = getSeriesGenreUseCase.executeGenreSeriesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeriesGenre.value = SeriesState(seriesGenre = it.data?.genres ?: emptyList())
                }
                is Resource.Error -> {
                    _stateSeriesGenre.value = SeriesState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateSeriesGenre.value = SeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}