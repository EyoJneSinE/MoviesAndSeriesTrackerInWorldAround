package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSearchSeriesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.event.SeriesEvent
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
class SeriesSearchViewModel @Inject constructor(
    private val getSearchSeriesUseCase: GetSearchSeriesUseCase
): ViewModel() {

    private val _stateSearchSeries = MutableStateFlow<SeriesState>(SeriesState())
    val stateSearchSeries : StateFlow<SeriesState> = _stateSearchSeries

    private var jobSearchSeries : Job? = null

    init {
        getSearchSeries(_stateSearchSeries.value.search)
    }

    private fun getSearchSeries(search: String) {
        jobSearchSeries?.cancel()
        jobSearchSeries = getSearchSeriesUseCase.executeSearchSerieFromTMDB(search).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSearchSeries.value = SeriesState(searchingSeries = it.data)
                }

                is Resource.Error -> {
                    _stateSearchSeries.value = SeriesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateSearchSeries.value = SeriesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(eventSeries : SeriesEvent) {
        when(eventSeries) {
            is SeriesEvent.SearchSeries -> {
                getSearchSeries(eventSeries.searchSeries)
            }
        }
    }
}