package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesDetailsCastAndCrewUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.state.SeriesDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesCrewViewModel @Inject constructor(
    private val seriesCrewUseCase: GetSeriesDetailsCastAndCrewUseCase,
    private val savedSeriesCrewStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateSeriesCrew = MutableStateFlow<SeriesDetailState>(SeriesDetailState())
    val stateSeriesCrew : StateFlow<SeriesDetailState> = _stateSeriesCrew

    private var jobSeriesCrew : Job? = null

    init {
        savedSeriesCrewStateHandle.get<String>(Constants.SERIES_ID.toString())?.let {
            getSeriesCrew(it.toInt())
        }
    }

    private fun getSeriesCrew(seriesId: Int) {
        jobSeriesCrew?.cancel()
        jobSeriesCrew = seriesCrewUseCase.executeGetSeriesCastFromTMDB(seriesId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeriesCrew.value = SeriesDetailState(seriesCrew = it.data?.crew ?: emptyList())
                }
                is Resource.Error -> {
                    _stateSeriesCrew.value = SeriesDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateSeriesCrew.value = SeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}